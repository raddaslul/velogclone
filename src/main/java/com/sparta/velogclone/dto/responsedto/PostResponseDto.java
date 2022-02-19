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
    private int commentCnt;
    private int likeCnt;
    private String postUserName;

    public PostResponseDto(
            Post post,
            int commentCnt,
            int likeCnt,
            String postModifiedAt) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postModifiedAt = postModifiedAt;
        this.commentCnt = commentCnt;
        this.likeCnt = likeCnt;
        this.postUserName = post.getUser().getUserName();
    }
}