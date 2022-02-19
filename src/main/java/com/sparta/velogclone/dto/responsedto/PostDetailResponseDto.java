package com.sparta.velogclone.dto.responsedto;

import com.sparta.velogclone.domain.Post;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class PostDetailResponseDto {
    private Long postId;
    private String imageUrl;
    private String title;
    private String content;
    private String postModifiedAt;
    private int commentCnt;
    private int likeCnt;
    private String postUserName;
    private List<CommentResponseDto> commentList;

    public PostDetailResponseDto (
            Post post,
            int commentCnt,
            int likeCnt,
            String postModifiedAt,
            List<CommentResponseDto> commentList) {
        this.postId = post.getId();
        this.imageUrl = post.getImageFile().getFilePath();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postModifiedAt = postModifiedAt;
        this.commentCnt = commentCnt;
        this.likeCnt = likeCnt;
        this.postUserName = post.getUser().getUserName();
        this.commentList = commentList;
    }
}
