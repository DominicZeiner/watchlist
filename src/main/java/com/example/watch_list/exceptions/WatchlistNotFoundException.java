package com.example.watch_list.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class WatchlistNotFoundException extends CustomException {
    public WatchlistNotFoundException(String imdbId) {
        super(Collections.singletonList("Watchlist entry with imdb id " + imdbId + " not found"), HttpStatus.NOT_FOUND);
    }
}
