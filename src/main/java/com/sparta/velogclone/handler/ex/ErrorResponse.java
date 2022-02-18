package com.sparta.velogclone.handler.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {

    private String code;
    private String message;

}