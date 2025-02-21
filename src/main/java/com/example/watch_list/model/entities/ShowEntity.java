package com.example.watch_list.model.entities;

import jakarta.persistence.Entity;

import java.time.Year;

@Entity
public class ShowEntity extends MediaEntity {

    private Integer seasons;
    private Integer episodes;
    private Year startYear;
    private Year endYear;

    public ShowEntity() {
    }

    public ShowEntity(String imdbId, String title, String genre, Double imdbRating, Integer runtime, int seasons, int episodes, Year startYear, Year endYear) {
        super(
                imdbId,
                title,
                genre,
                imdbRating,
                runtime);
        this.seasons = seasons;
        this.episodes = episodes;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public Integer getSeasons() {
        return seasons;
    }

    public void setSeasons(Integer seasons) {
        this.seasons = seasons;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public Year getStartYear() {
        return startYear;
    }

    public void setStartYear(Year startYear) {
        this.startYear = startYear;
    }

    public Year getEndYear() {
        return endYear;
    }

    public void setEndYear(Year endYear) {
        this.endYear = endYear;
    }
}
