package com.example.watch_list.service;

import com.example.watch_list.exceptions.MediaAlreadyExistsException;
import com.example.watch_list.exceptions.MediaNotFoundException;
import com.example.watch_list.model.entities.MediaEntity;
import com.example.watch_list.model.entities.MovieEntity;
import com.example.watch_list.model.entities.ShowEntity;
import com.example.watch_list.model.entries.CreateMedia;
import com.example.watch_list.model.entries.MediaEntry;
import com.example.watch_list.model.mapper.MediaMapper;
import com.example.watch_list.repository.MediaRepository;
import com.example.watch_list.repository.MovieRepository;
import com.example.watch_list.repository.ShowRepository;
import com.example.watch_list.service.omdb.OmdbResponse;
import com.example.watch_list.service.omdb.OmdbService;
import com.example.watch_list.util.MediaUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;
    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;
    private final MediaUtil mediaUtil;
    private final OmdbService omdbService;
    private final MediaMapper mediaMapper;

    public MediaService(MediaRepository mediaRepository, MovieRepository movieRepository, ShowRepository showRepository, MediaUtil mediaUtil, OmdbService omdbService, MediaMapper mediaMapper) {
        this.mediaRepository = mediaRepository;
        this.movieRepository = movieRepository;
        this.showRepository = showRepository;
        this.mediaUtil = mediaUtil;
        this.omdbService = omdbService;
        this.mediaMapper = mediaMapper;
    }



    public MediaEntry addMedia(MediaEntity mediaEntity) {
        return null;
    }


    public MediaEntry addMediaByImdbId(CreateMedia media) throws MediaAlreadyExistsException {

        if (mediaUtil.doesMediaExist(media.imdbId()).isPresent()) {
            throw new MediaAlreadyExistsException("Media with imdbId " + media.imdbId() + " already exists");
        }

        OmdbResponse omdbResponse = omdbService.fetchResponseByImdbId(media.imdbId());

        MediaEntity mediaEntity = omdbService.convertOmdbResponseToMediaEntity(omdbResponse);

        if (mediaEntity instanceof MovieEntity) {
            MovieEntity movieEntity = this.movieRepository.save((MovieEntity) mediaEntity);
            return mediaMapper.toMediaEntry(movieEntity);
        } else {
            ShowEntity showEntity = this.showRepository.save((ShowEntity) mediaEntity);
            return mediaMapper.toMediaEntry(showEntity);
        }
    }

    @Transactional
    public void deleteMedia(String imdbId) throws MediaNotFoundException {
        if (mediaUtil.doesMediaExist(imdbId).isEmpty()) {
            throw new MediaNotFoundException("Media with imdbId " + imdbId + " does not exist");
        }
        mediaRepository.deleteByImdbId(imdbId);
    }

    public MediaEntry getMediaByImdbId(String imdbId) throws MediaNotFoundException {
        Optional<MediaEntity> mediaEntity = mediaRepository.findByImdbId(imdbId);
        if (mediaEntity.isEmpty()) {
            throw new MediaNotFoundException("Media with imdbId " + imdbId + " does not exist");
        }
        return mediaMapper.toMediaEntry(mediaEntity.get());
    }


    public List<MediaEntry> getAllMedia() {
        return mediaRepository.findAll().stream()
                .map(mediaMapper::toMediaEntry)
                .collect(Collectors.toList());
    }
}
