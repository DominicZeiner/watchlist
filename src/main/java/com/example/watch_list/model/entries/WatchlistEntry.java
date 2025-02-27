package com.example.watch_list.model.entries;

import com.example.watch_list.model.entities.WatchlistStatus;

import java.time.LocalDate;

public record WatchlistEntry(
        String imdbId,
        WatchlistStatus status,
        LocalDate dateWatched,
        Integer episodesWatched,
        String notes,
        boolean isFavorite


)

{
}
