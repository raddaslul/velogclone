package com.sparta.velogclone.contoller;

import com.sparta.velogclone.config.auth.UserDetailsImpl;
import com.sparta.velogclone.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikesController {

    private final LikesService likesService;

    @Autowired
    public LikesController (LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping("/api/likes/{postId}")
    public ResponseEntity<?> addLikes(@PathVariable Long postId) {
        return likesService.addLikes(postId);
    }
}
