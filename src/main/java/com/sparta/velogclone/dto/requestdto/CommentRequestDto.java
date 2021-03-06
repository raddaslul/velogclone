package com.sparta.velogclone.dto.requestdto;

import com.sparta.velogclone.domain.Comment;
import com.sparta.velogclone.domain.Post;
import com.sparta.velogclone.domain.User;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private String comment;
    private Long postId;

    public Comment toEntity(Post post, User user) {
        return Comment.builder()
                .comment(this.comment)
                .post(post)
                .user(user)
                .build();
    }
}
