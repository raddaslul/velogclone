package com.sparta.velogclone.service;

import com.sparta.velogclone.domain.*;
import com.sparta.velogclone.dto.requestdto.PostRequestDto;
import com.sparta.velogclone.dto.responsedto.CMResponseDto;
import com.sparta.velogclone.dto.responsedto.PostResponseDto;
import com.sparta.velogclone.repository.CommentRepository;
import com.sparta.velogclone.repository.LikesRepository;
import com.sparta.velogclone.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikesRepository likesRepository;
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
        List<PostResponseDto> postList = new ArrayList<>();
        List<Post> posts = postRepository.findAllByOOrderByModifiedAtDesc();

        for (Post post : posts) {
            List<Comment> comments = commentRepository.findAllByPostIdOrderByModifiedAtDesc(post.getId());
            int commentCnt = comments.size();
            List<Likes> likes = likesRepository.findAllByPostId(post.getId());
            int likeCnt = likes.size();
            PostResponseDto postResponseDto = new PostResponseDto(post, commentCnt, likeCnt);
            postList.add(postResponseDto);
        }
        return postList;
    }
}
