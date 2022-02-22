package com.sparta.velogclone.repository;

import com.sparta.velogclone.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    List<Post> findAllByOrderByModifiedAtDesc();
    List<Post> findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String searchWord, String search);
}