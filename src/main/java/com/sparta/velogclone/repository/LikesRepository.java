package com.sparta.velogclone.repository;

import com.sparta.velogclone.domain.Likes;
import com.sparta.velogclone.domain.Post;
import com.sparta.velogclone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
}