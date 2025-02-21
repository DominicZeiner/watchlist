package com.example.watch_list.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String imdbId;
    @NotBlank
    @NotNull
    private String title;
    private String genre;
    @Min(0)
    @Min(10)
    private Double imdbRating;
    @Min(0)
    private Integer runtime;
    private final LocalDateTime createdAt = LocalDateTime.now();


    public MediaEntity() {
    }

    public MediaEntity(String imdbId, String title, String genre, Double imdbRating, Integer runtime) {
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
