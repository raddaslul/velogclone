package com.sparta.velogclone.dto.responsedto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    private Long id;
    private String comment;
    private String commentModifiedAt;
    private Long postId;
    private Long userId;
    private String userName;
}
