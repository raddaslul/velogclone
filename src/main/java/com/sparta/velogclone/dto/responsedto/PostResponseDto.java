package com.sparta.velogclone.dto.responsedto;

import lombok.Data;

@Data
public class PostResponseDto {
    private final Long id;
    private final String imageUrl;
    private final String title;
    private final String content;
    private final String postModifiedAt;
    private final int CommentCnt;
    private final int likeCnt;
    private final String userUserName;
}