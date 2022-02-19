package com.sparta.velogclone.domain;

import com.sparta.velogclone.dto.requestdto.CommentRequestDto;
import com.sparta.velogclone.dto.responsedto.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "comment")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "comment", nullable = false, length = 200)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updateComment(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }

    public CommentResponseDto toResponseDto() {
        return CommentResponseDto.builder()
                .commentId(this.id)
                .comment(this.comment)
                .commentModifiedAt(this.getModifiedAt().toString())
                .commentUserId(this.user.getId())
                .commentUserName(this.user.getUserName())
                .build();
    }
}