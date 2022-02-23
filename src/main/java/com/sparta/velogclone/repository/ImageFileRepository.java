package com.sparta.velogclone.repository;

import com.sparta.velogclone.domain.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
    List<ImageFile> findAllByPostId(Long postId);
}