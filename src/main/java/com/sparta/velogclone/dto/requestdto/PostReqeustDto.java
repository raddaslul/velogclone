package com.sparta.velogclone.dto.requestdto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostReqeustDto implements Serializable {
    private final String title;
    private final String content;
    private final String imageUrl;
    private final int likeCnt;
}
