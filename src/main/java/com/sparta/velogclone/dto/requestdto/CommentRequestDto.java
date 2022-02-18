package com.sparta.velogclone.dto.requestdto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentRequestDto {
    private final String comment;
    private final Long postId;
}
