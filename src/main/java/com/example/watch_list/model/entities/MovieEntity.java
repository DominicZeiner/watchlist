package com.example.watch_list.model.entities;

import jakarta.persistence.Entity;

import java.time.Year;

@Entity
public class MovieEntity extends MediaEntity {

    Year releaseYear;

    public MovieEntity() {
    }

    public MovieEntity(String imdbId, String title, String genre, Double imdbRating, Integer runtime, Year releaseYear) {
        super(
                imdbId,
                title,
                genre,
                imdbRating,
                runtime);
        this.releaseYear = releaseYear;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
        this.releaseYear = releaseYear;
    }


}
