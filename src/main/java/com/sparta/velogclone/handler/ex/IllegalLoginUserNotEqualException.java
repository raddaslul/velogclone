package com.sparta.velogclone.handler.ex;

public class IllegalLoginUserNotEqualException extends RuntimeException {
    public IllegalLoginUserNotEqualException(String message) {
        super(message);
    }
}
