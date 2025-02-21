package com.example.watch_list.service;


import com.example.watch_list.exceptions.MediaNotFoundException;
import com.example.watch_list.exceptions.WatchlistEntryAlreadyExistsException;
import com.example.watch_list.model.entities.MediaEntity;
import com.example.watch_list.model.entities.MovieEntity;
import com.example.watch_list.model.entities.WatchlistEntity;
import com.example.watch_list.model.entities.WatchlistStatus;
import com.example.watch_list.model.entries.AvailableMedia;
import com.example.watch_list.model.entries.CreateWatchlist;
import com.example.watch_list.model.entries.GetWatchListWithMedia;
import com.example.watch_list.model.entries.WatchListEntry;
import com.example.watch_list.model.mapper.WatchlistMapper;
import com.example.watch_list.repository.MediaRepository;
import com.example.watch_list.repository.WatchlistRepository;
import com.example.watch_list.util.MediaUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WatchlistService {

    WatchlistRepository watchlistRepository;
    MediaRepository mediaRepository;
    MediaUtil mediaUtil;
    WatchlistMapper watchlistMapper;

    public WatchlistService(WatchlistRepository watchlistRepository, MediaUtil mediaUtil, MediaRepository mediaRepository, WatchlistMapper watchlistMapper) {
        this.watchlistRepository = watchlistRepository;
        this.mediaUtil = mediaUtil;
        this.mediaRepository = mediaRepository;
        this.watchlistMapper = watchlistMapper;
    }

    @Transactional
    public void deleteWatchlistEntry(String imdbId) {
        watchlistRepository.deleteByMediaEntityId(mediaRepository.findByImdbId(imdbId).get().getId());
    }

    public void updateWatchlistEntry() {
        return;
    }

    public WatchListEntry addWatchlistEntry(CreateWatchlist createWatchlist) throws WatchlistEntryAlreadyExistsException, MediaNotFoundException {
        Optional<MediaEntity> mediaEntity = mediaRepository.findByImdbId(createWatchlist.imdbId());
        if (mediaEntity.isEmpty()) {
            throw new MediaNotFoundException("Media with imdbId " + createWatchlist.imdbId() + " not found");
        }

        if (watchlistRepository.findByMediaEntityId(mediaEntity.get().getId()).isPresent()) {
            throw new WatchlistEntryAlreadyExistsException("Watchlist entry with imdbId " + createWatchlist.imdbId() + " already exists");
        }


        WatchlistEntity watchlistEntity = new WatchlistEntity(
                mediaEntity.get(),
                WatchlistStatus.PLAN_TO_WATCH,
                0,
                LocalDate.now(),
                "",
                null,
                false
        );

        watchlistRepository.save(watchlistEntity);
        return watchlistMapper.toWatchlistEntry(watchlistEntity);
    }

    public List<WatchListEntry> getWatchlistEntries() {
        return null;
    }

    public List<GetWatchListWithMedia> getWatchlistWithMedia() {
        List<WatchlistEntity> watchlistEntities = watchlistRepository.findAll();

        return watchlistEntities.stream()
                .map(watchlistEntity -> {
                    Optional<MediaEntity> mediaEntityOptional = mediaRepository.findByImdbId(watchlistEntity.getMediaEntity().getImdbId());

                    return mediaEntityOptional.map(mediaEntity ->
                            new GetWatchListWithMedia(
                                    mediaEntity.getImdbId(),
                                    mediaEntity.getTitle(),
                                    mediaEntity.getImdbRating(),
                                    mediaEntity.getRuntime(),
                                    watchlistEntity.getStatus(),
                                    watchlistEntity.getDateWatched(),
                                    watchlistEntity.getEpisodesWatched(),
                                    watchlistEntity.getNotes(),
                                    watchlistEntity.isFavorite(),
                                    mediaEntity instanceof MovieEntity ? "movie" : "show"
                            )
                    ).orElse(null);
                })
                .filter(Objects::nonNull)
                .toList();
    }


    public List<WatchListEntry> getWatchedEntries() {
        return watchlistRepository.findByStatus(WatchlistStatus.WATCHED).stream()
                .map(watchlistMapper::toWatchlistEntry)
                .collect(Collectors.toList());
    }

    public List<WatchListEntry> getFavoriteEntries() {
        return watchlistRepository.findByIsFavorite(true).stream()
                .map(watchlistMapper::toWatchlistEntry)
                .collect(Collectors.toList());
    }

    public List<AvailableMedia> getImdbIdsNotInWatchlist() {
        List<String> imdbIdsInWatchlist = watchlistRepository.findAll().stream()
                .map(watchlistEntity -> watchlistEntity.getMediaEntity().getImdbId())
                .toList();

        return mediaRepository.findAll().stream()
                .map(mediaEntity -> new AvailableMedia(mediaEntity.getImdbId(), mediaEntity.getTitle()))
                .filter(availableMedia -> !imdbIdsInWatchlist.contains(availableMedia.imdbId()))
                .toList();
    }
}
