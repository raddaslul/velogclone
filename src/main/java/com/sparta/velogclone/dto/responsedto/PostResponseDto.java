package com.sparta.velogclone.dto.responsedto;

import com.sparta.velogclone.domain.Post;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class PostResponseDto {
    private Long postId;
    private String imageUrl;
    private String title;
    private String content;
    private String postModifiedAt;
    private int CommentCnt;
    private int likeCnt;
    private String postUserName;

    public PostResponseDto(Post post, int CommentCnt, int likeCnt) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postModifiedAt = post
    }
}