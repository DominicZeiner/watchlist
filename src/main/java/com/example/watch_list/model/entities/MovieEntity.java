package com.example.watch_list.model.entities;

import jakarta.persistence.Entity;

@Entity
public class MovieEntity extends MediaEntity {

    Integer releaseYear;

    public MovieEntity() {
    }

    public MovieEntity(String imdbId, String title, String genre, Double imdbRating, Integer runtime, int releaseYear) {
        super(imdbId, title, genre, imdbRating, runtime);
        this.releaseYear = releaseYear;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }


}
