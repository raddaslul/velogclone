package com.sparta.velogclone.repository;

import com.sparta.velogclone.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}