package com.sparta.velogclone.service;

import com.sparta.velogclone.domain.ImageFile;
import com.sparta.velogclone.domain.Post;
import com.sparta.velogclone.domain.User;
import com.sparta.velogclone.dto.requestdto.PostRequestDto;
import com.sparta.velogclone.dto.responsedto.CMResponseDto;
import com.sparta.velogclone.dto.responsedto.PostResponseDto;
import com.sparta.velogclone.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ImageFileService imageFileService;

    // 게시물 등록
    @Transactional
    public void savePost(MultipartFile multipartFile, PostRequestDto postRequestDto, User user) throws IOException {
        Post post = new Post(postRequestDto, user);
        postRepository.save(post);

        ImageFile imageFile = imageFileService.saveFile(multipartFile);

        postRequestDto.setImageFile(imageFile);
        imageFile.addPost(post);
    }

    // 게시믈 전체 조회
    public List<PostResponseDto> viewPost() {

        return postList;
    }
}
