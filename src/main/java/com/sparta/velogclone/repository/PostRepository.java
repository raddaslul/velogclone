package com.sparta.velogclone.repository;

import com.sparta.velogclone.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
//    List<Post> findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String searchWord);
}