package com.example.watch_list.model.entries;

import com.example.watch_list.model.entities.WatchlistStatus;

public record GetWatchListWithMedia (
        String imdbId,
        String title,
        Double imdbRating,
        Integer runtime,
        WatchlistStatus status,
        java.time.LocalDate dateWatched,
        Integer episodesWatched,
        String notes,
        boolean isFavorite,
        String type

){

}
