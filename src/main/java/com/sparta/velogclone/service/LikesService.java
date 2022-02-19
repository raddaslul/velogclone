package com.sparta.velogclone.service;

import com.sparta.velogclone.domain.Likes;
import com.sparta.velogclone.domain.Post;
import com.sparta.velogclone.repository.LikesRepository;
import com.sparta.velogclone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final PostRepository postRepository;

    @Autowired
    public LikesService (LikesRepository likesRepository,
                         PostRepository postRepository) {
        this.likesRepository = likesRepository;
        this.postRepository = postRepository;
    }


    public ResponseEntity<?> addLikes(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        );

        Likes likes = Likes.builder()
                .post(post)
                .build();

        likesRepository.save(likes);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
