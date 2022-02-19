package com.sparta.velogclone.dto.responsedto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
public class CommentResponseDto {
    private Long commentId;
    private String comment;
    private String commentModifiedAt;
    private Long commentUserId;
    private String commentUserName;
}
