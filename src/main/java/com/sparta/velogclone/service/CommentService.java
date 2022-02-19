package com.sparta.velogclone.service;

import com.sparta.velogclone.config.auth.UserDetailsImpl;
import com.sparta.velogclone.domain.Comment;
import com.sparta.velogclone.domain.Post;
import com.sparta.velogclone.domain.User;
import com.sparta.velogclone.dto.requestdto.CommentRequestDto;
import com.sparta.velogclone.handler.ex.CommentNotFoundException;
import com.sparta.velogclone.handler.ex.IllegalCommentDeleteUserException;
import com.sparta.velogclone.handler.ex.IllegalCommentUpdateUserException;
import com.sparta.velogclone.handler.ex.LoginUserNotFoundException;
import com.sparta.velogclone.repository.CommentRepository;
import com.sparta.velogclone.repository.PostRepository;
import com.sparta.velogclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void writeComment(CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
                () -> new CommentNotFoundException("댓글이 존재하지 않습니다.")
        );

        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new LoginUserNotFoundException("로그인 한 유저가 아닙니다.")
        );

        commentRepository.save(commentRequestDto.toEntity(post, user));
    }

    @Transactional
    public void changeComment(Long commentId,
                                           CommentRequestDto commentRequestDto,
                                           UserDetailsImpl userDetails) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CommentNotFoundException("댓글이 존재하지 않습니다.")
        );

        if (!userDetails.getUser().equals(comment.getUser())) {
            throw new IllegalCommentUpdateUserException("본인 댓글이 아니면 수정할 수 없습니다.");
        }

        comment.updateComment(commentRequestDto);

        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, UserDetailsImpl userDetails) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CommentNotFoundException("댓글이 존재하지 않습니다.")
        );

        if (!userDetails.getUser().equals(comment.getUser())) {
            throw new IllegalCommentDeleteUserException("본인 댓글이 아니면 삭제할 수 없습니다.");
        }

        commentRepository.deleteById(commentId);
    }
}
