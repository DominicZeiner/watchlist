package com.example.watch_list.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class WatchlistEntryAlreadyExistsException extends CustomException {
    public WatchlistEntryAlreadyExistsException(String message) {
        super(Collections.singletonList(message), HttpStatus.CONFLICT);
    }
}
