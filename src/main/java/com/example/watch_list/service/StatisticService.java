package com.example.watch_list.service;

import com.example.watch_list.model.entities.MediaEntity;
import com.example.watch_list.model.entities.WatchlistEntity;
import com.example.watch_list.model.entities.WatchlistStatus;
import com.example.watch_list.repository.MediaRepository;
import com.example.watch_list.repository.MovieRepository;
import com.example.watch_list.repository.ShowRepository;
import com.example.watch_list.repository.WatchlistRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    MediaRepository mediaRepository;
    MovieRepository movieRepository;
    ShowRepository showRepository;
    WatchlistRepository watchlistRepository;

    public StatisticService(MediaRepository mediaRepository, MovieRepository movieRepository, ShowRepository showRepository, WatchlistRepository watchlistRepository) {
        this.mediaRepository = mediaRepository;
        this.movieRepository = movieRepository;
        this.showRepository = showRepository;
        this.watchlistRepository = watchlistRepository;
    }


    public Integer getTimeSpent() {
        return (getTimeSpentOnMovies() + getTimeSpentOnShows());
    }

    public Integer getTimeSpentOnShows() {
        int timeSpentOnShows;
        timeSpentOnShows = showRepository.findAll().stream() // episodes * runtime
                .filter(showEntity -> watchlistRepository.findByMediaEntityId(showEntity.getId()).get().getStatus() == WatchlistStatus.WATCHED)
                .mapToInt(showEntity -> showEntity.getEpisodes() * showEntity.getRuntime()).sum();
        return timeSpentOnShows;
    }

    public Integer getTimeSpentOnMovies() {
        int timeSpentOnMovies;
        timeSpentOnMovies = movieRepository.findAll().stream()
                .filter(movieEntity -> watchlistRepository.findByMediaEntityId(movieEntity.getId()).get().getStatus() == WatchlistStatus.WATCHED)
                .mapToInt(MediaEntity::getRuntime).sum();
        return timeSpentOnMovies;
    }


    public Double getAverageImdbRating() {
        return mediaRepository.findAll().stream()
                .mapToDouble(MediaEntity::getImdbRating)
                .average()
                .orElse(0.0);
    }

    public Double getAverageGivenRating() {
        return watchlistRepository.findAll().stream()
                .mapToDouble(WatchlistEntity::getRating)
                .average()
                .orElse(0.0);
    }
}
