package com.sparta.velogclone.contoller;

import com.sparta.velogclone.config.auth.UserDetailsImpl;
import com.sparta.velogclone.dto.requestdto.CommentRequestDto;
import com.sparta.velogclone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController (CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/comment")
    public HashMap<String, Object> writeComment(@RequestBody CommentRequestDto commentRequestDto,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {

        HashMap<String, Object> result = new HashMap<>();

        commentService.writeComment(commentRequestDto, userDetails);
        result.put("result", true);

        return result;
    }

    @PutMapping("/api/comment/{commentId}")
    public HashMap<String, Object> changeComment(@PathVariable Long commentId,
                                           @RequestBody CommentRequestDto commentRequestDto,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {

        HashMap<String, Object> result = new HashMap<>();

        commentService.changeComment(commentId, commentRequestDto, userDetails);
        result.put("result", true);

        return result;
    }

    @DeleteMapping("api/comment/{commentId}")
    public HashMap<String, Object> deleteComment(@PathVariable Long commentId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {

        HashMap<String, Object> result = new HashMap<>();

        commentService.deleteComment(commentId, userDetails);
        result.put("result", true);

        return result;
    }
}
