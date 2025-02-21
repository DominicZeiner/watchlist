package com.example.watch_list.model.entries;

public record MediaEntry(
        String imdbId,
        String title,
        String genre,
        Double imdbRating,
        Integer runtime) {
}
