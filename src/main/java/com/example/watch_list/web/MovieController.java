package com.example.watch_list.web;

import com.example.watch_list.model.entities.MovieEntity;
import com.example.watch_list.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieEntity> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{imdbId}")
    public MovieEntity getMovieByImdbId(@PathVariable String imdbId) {
        return movieService.getMovieByImdbId(imdbId);
    }






}
