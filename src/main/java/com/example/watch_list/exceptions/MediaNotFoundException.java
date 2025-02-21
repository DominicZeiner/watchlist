package com.example.watch_list.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class MediaNotFoundException extends CustomException{

    public MediaNotFoundException(String message){
        super(Collections.singletonList(message), HttpStatus.NOT_FOUND);
    }
}
