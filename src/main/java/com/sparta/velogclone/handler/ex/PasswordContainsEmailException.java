package com.sparta.velogclone.handler.ex;

import lombok.Getter;

@Getter
public class PasswordContainsEmailException extends RuntimeException {
    public PasswordContainsEmailException(String message) {
        super(message);
    }
}
