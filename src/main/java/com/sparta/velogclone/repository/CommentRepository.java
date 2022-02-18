package com.sparta.velogclone.repository;

import com.sparta.velogclone.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}