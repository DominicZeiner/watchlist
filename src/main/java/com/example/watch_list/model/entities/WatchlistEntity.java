package com.example.watch_list.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class WatchlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private MediaEntity mediaEntity;
    @Enumerated(EnumType.STRING)
    private WatchlistStatus status;
    private Integer episodesWatched;
    private LocalDate dateWatched;
    private String notes;
    private @Min(0) @Max(10)
    Double Rating;
    private boolean isFavorite;
    private final LocalDate createdAt = LocalDate.now();

    public WatchlistEntity() {
    }

    public WatchlistEntity(MediaEntity mediaEntity, WatchlistStatus status, Integer episodesWatched, LocalDate dateWatched, String notes, @Min(0) @Min(10) Double rating, boolean isFavorite) {
        this.mediaEntity = mediaEntity;
        this.status = status;
        this.episodesWatched = episodesWatched;
        this.dateWatched = dateWatched;
        this.notes = notes;
        this.Rating = rating;
        this.isFavorite = isFavorite;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MediaEntity getMediaEntity() {
        return mediaEntity;
    }

    public void setMediaEntity(MediaEntity mediaEntity) {
        this.mediaEntity = mediaEntity;
    }

    public WatchlistStatus getStatus() {
        return status;
    }

    public void setStatus(WatchlistStatus status) {
        this.status = status;
    }

    public Integer getEpisodesWatched() {
        return episodesWatched;
    }

    public void setEpisodesWatched(Integer episodesWatched) {
        this.episodesWatched = episodesWatched;
    }

    public LocalDate getDateWatched() {
        return dateWatched;
    }

    public void setDateWatched(LocalDate dateWatched) {
        this.dateWatched = dateWatched;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
