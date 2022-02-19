package com.sparta.velogclone.dto.responsedto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponseDto {
    private final Long commentId;
    private final String comment;
    private final String commentModifiedAt;
    private final Long commentUserId;
    private final String commentUserName;
}
