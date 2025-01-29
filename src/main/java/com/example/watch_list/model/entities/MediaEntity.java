package com.example.watch_list.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public abstract class MediaEntity {

    @Id
    private UUID id;
    private String imdbId;
    private String title;
    private String genre;
    private Double imdbRating;
    private Integer runtime;

    public MediaEntity() {
    }

    public MediaEntity(String imdbId, String title, String genre, Double imdbRating, Integer runtime) {
        this.id = UUID.randomUUID();
        this.imdbId = imdbId;
        this.title = title;
        this.genre = genre;
        this.imdbRating = imdbRating;
        this.runtime = runtime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }
}
