package com.sparta.velogclone.dto.responsedto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    private final Long id;
    private final String comment;
    private final String commentModifiedAt;
    private final Long postId;
    private final Long userId;
    private final String userName;
}
