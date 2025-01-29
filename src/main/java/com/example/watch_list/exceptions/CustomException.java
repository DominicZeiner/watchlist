package com.example.watch_list.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public class CustomException extends Exception {

    private final List<String> errors;

    private final HttpStatus statusCode;

    public CustomException(List<String> errors, HttpStatus status) {
        this.errors = errors;
        this.statusCode = status;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }
}
