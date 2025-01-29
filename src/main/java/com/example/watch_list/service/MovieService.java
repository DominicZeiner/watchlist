package com.example.watch_list.service;

import com.example.watch_list.model.entities.MovieEntity;
import com.example.watch_list.model.mapper.MovieMapper;
import com.example.watch_list.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    public MovieEntity getMovieByImdbId(String imdbId) {
        return movieRepository.findByImdbId(imdbId).orElseThrow();
    }

    public MovieEntity saveMovie(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    public void deleteMovie(String imdbId) {
        movieRepository.deleteByImdbId(imdbId);
    }


}
