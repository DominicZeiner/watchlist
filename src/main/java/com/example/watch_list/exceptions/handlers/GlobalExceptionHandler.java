package com.example.watch_list.exceptions.handlers;

import com.example.watch_list.exceptions.ApiErrorWrapper;
import com.example.watch_list.exceptions.CustomException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ApiErrorWrapper> handleBadRequestException(CustomException exception) {
        return new ResponseEntity<>(
                new ApiErrorWrapper(exception.getErrors()),
                new HttpHeaders(),
                exception.getStatusCode()
        );
    }
}
