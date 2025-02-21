package com.example.watch_list.model.entries;

import java.time.Year;

public record ShowEntry(
        String imdbId,
        String title,
        String genre,
        Double imdbRating,
        Integer runtime,
        Integer seasons,
        Integer episodes,
        Year startYear,
        Year endYear

) {
}
