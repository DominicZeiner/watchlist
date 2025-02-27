package com.example.watch_list.model.mapper;

import com.example.watch_list.model.entities.WatchlistEntity;
import com.example.watch_list.model.entries.WatchlistEntry;
import org.springframework.stereotype.Component;

@Component
public class WatchlistMapper {
    public WatchlistEntry toWatchlistEntry(WatchlistEntity watchlistEntity) {
        return new WatchlistEntry(
                watchlistEntity.getMediaEntity().getImdbId(),
                watchlistEntity.getStatus(),
                watchlistEntity.getDateWatched(),
                watchlistEntity.getEpisodesWatched(),
                watchlistEntity.getNotes(),
                watchlistEntity.isFavorite()
        );
    }

}
