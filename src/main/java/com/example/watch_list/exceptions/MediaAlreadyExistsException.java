package com.example.watch_list.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public class MediaAlreadyExistsException extends CustomException{

    public MediaAlreadyExistsException(String message){
        super(Collections.singletonList(message), HttpStatus.CONFLICT);
    }
}


