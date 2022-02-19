package com.sparta.velogclone.repository;

import com.sparta.velogclone.domain.Likes;
import com.sparta.velogclone.domain.Post;
import com.sparta.velogclone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findAllByPostId(Long id);
}