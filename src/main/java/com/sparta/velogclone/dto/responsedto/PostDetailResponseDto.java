package com.sparta.velogclone.dto.responsedto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDetailResponseDto {
    private final Long id;
    private final String imageUrl;
    private final String title;
    private final String content;
    private final String modifiedAt;
    private final int CommentCnt;
    private final int likeCnt;
    private final String userUserName;
    private final List<CommentResponseDto> commentList;
}
