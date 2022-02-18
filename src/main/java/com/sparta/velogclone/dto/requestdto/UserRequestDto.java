package com.sparta.velogclone.dto.requestdto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequestDto {
    private final String userEmail;
    private final String userName;
    private final String password;
    private final String passwordCheck;
}
