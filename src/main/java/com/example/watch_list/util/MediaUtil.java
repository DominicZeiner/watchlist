package com.example.watch_list.util;

import com.example.watch_list.repository.MovieRepository;
import com.example.watch_list.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MediaUtil {

    MovieRepository movieRepository;
    ShowRepository showRepository;

    public MediaUtil(MovieRepository movieRepository, ShowRepository showRepository) {
        this.movieRepository = movieRepository;
        this.showRepository = showRepository;
    }

    public Optional<String> doesMediaExist(String imdbId) {
        if (movieRepository.existsByImdbId(imdbId)) {
            return Optional.of("movie");
        } else if (showRepository.existsByImdbId(imdbId)) {
            return Optional.of("show");
        } else {
            return Optional.empty();
        }
    }

}
