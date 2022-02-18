package com.sparta.velogclone.handler.ex;

import lombok.Getter;

@Getter
public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException(String message) {
        super(message);
    }
}