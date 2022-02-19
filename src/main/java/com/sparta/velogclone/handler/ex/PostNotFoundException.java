package com.sparta.velogclone.handler.ex;

import lombok.Getter;

@Getter
public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message) {
        super(message);
    }
}
