package com.sparta.velogclone.handler.ex;

import lombok.Getter;

@Getter
public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}