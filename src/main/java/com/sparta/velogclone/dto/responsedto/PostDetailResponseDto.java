package com.sparta.velogclone.dto.responsedto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Getter
@Setter
public class PostDetailResponseDto {
    private final Long postId;
    private final String imageUrl;
    private final String title;
    private final String content;
    private final String postModifiedAt;
    private final int CommentCnt;
    private final int likeCnt;
    private final String postUserName;
    private final List<CommentResponseDto> commentList;
}
