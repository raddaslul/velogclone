package com.sparta.velogclone.dto.responsedto;

import com.sparta.velogclone.domain.ImageFile;
import com.sparta.velogclone.domain.Post;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
public class PostDetailResponseDto {
    private Long postId;
    private List<String> imageUrlList;
    private String title;
    private String content;
    private String postModifiedAt;
    private int commentCnt;
    private int likeCnt;
    private String postUserName;
    private String postUserEmail;
    private List<CommentResponseDto> commentList;

    public PostDetailResponseDto (
            Post post,
            int commentCnt,
            int likeCnt,
            String postModifiedAt,
            List<CommentResponseDto> commentList) {
        this.postId = post.getId();
        this.imageUrlList = post.getImageFileList().stream().map(ImageFile::getFilePath).collect(Collectors.toList());
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postModifiedAt = postModifiedAt;
        this.commentCnt = commentCnt;
        this.likeCnt = likeCnt;
        this.postUserName = post.getUser().getUserName();
        this.postUserEmail = post.getUser().getUserEmail();
        this.commentList = commentList;
    }
}
