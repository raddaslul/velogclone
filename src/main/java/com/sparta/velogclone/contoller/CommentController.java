package com.sparta.velogclone.contoller;

import com.sparta.velogclone.config.auth.UserDetailsImpl;
import com.sparta.velogclone.dto.requestdto.CommentRequestDto;
import com.sparta.velogclone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController (CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public ResponseEntity<?> wirteComment(@RequestBody CommentRequestDto commentRequestDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.writeComment(commentRequestDto, userDetails);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<?> changeComment(@PathVariable Long commentId,
                                           @RequestBody CommentRequestDto commentRequestDto,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.changeComment(commentId, commentRequestDto, userDetails);
    }

    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails);
    }
}
