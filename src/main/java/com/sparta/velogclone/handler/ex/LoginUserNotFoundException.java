package com.sparta.velogclone.handler.ex;

import lombok.Getter;

@Getter
public class LoginUserNotFoundException extends RuntimeException {
    public LoginUserNotFoundException(String message) {
        super(message);
    }
}