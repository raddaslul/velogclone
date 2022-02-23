package com.sparta.velogclone.service;

import com.sparta.velogclone.domain.*;
import com.sparta.velogclone.dto.requestdto.ImageIdRequestDto;
import com.sparta.velogclone.dto.requestdto.PostRequestDto;
import com.sparta.velogclone.dto.responsedto.CommentResponseDto;
import com.sparta.velogclone.dto.responsedto.PostDetailResponseDto;
import com.sparta.velogclone.dto.responsedto.PostResponseDto;
import com.sparta.velogclone.handler.ex.IllegalPostDeleteUserException;
import com.sparta.velogclone.handler.ex.IllegalPostUpdateUserException;
import com.sparta.velogclone.handler.ex.PostNotFoundException;
import com.sparta.velogclone.repository.CommentRepository;
import com.sparta.velogclone.repository.ImageFileRepository;
import com.sparta.velogclone.repository.LikesRepository;
import com.sparta.velogclone.repository.PostRepository;
import com.sparta.velogclone.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ImageFileRepository imageFileRepository;
    private final LikesRepository likesRepository;

    private final S3Uploader s3Uploader;
    private final String imageDirName = "image";

    // 게시글 작성
    public List<ImageFile> savePost(PostRequestDto postRequestDto, User user) {
            Post post = new Post(postRequestDto, user);
            post = postRepository.save(post);
            List<ImageFile> imageFileList = new ArrayList<>();
            List<ImageIdRequestDto> imageIdRequestDtoList = postRequestDto.getImageIdList();
        for (ImageIdRequestDto imageIdRequestDto : imageIdRequestDtoList) {
            ImageFile imageFile = imageFileRepository.findById(imageIdRequestDto.getImageId())
                    .orElseThrow(IllegalArgumentException::new);
            imageFile.addPost(post);
            imageFileList.add(imageFile);
        } post.setImageFileList(imageFileList);
        return imageFileList;
    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> viewPost() {
        List<PostResponseDto> postList = new ArrayList<>();
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();

        for (Post post : posts) {
            List<Comment> comments = commentRepository.findAllByPostIdOrderByModifiedAtDesc(post.getId());
            int commentCnt = comments.size();

            List<Likes> likes = likesRepository.findAllByPostId(post.getId());
            int likeCnt = likes.size();

            String postModifiedAt = post.getModifiedAt().toString();
            String year = postModifiedAt.substring(0,4) + "년";
            String month = postModifiedAt.substring(5,7) + "월";
            String day = postModifiedAt.substring(8,10) + "일";
            postModifiedAt = year + month + day;

            PostResponseDto postResponseDto = new PostResponseDto(post, commentCnt, likeCnt, postModifiedAt);
            postList.add(postResponseDto);
        }
        return postList;
    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public PostDetailResponseDto viewPostDetail(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalAccessError::new);
        List<CommentResponseDto> commentList = commentRepository.findAllByPostIdOrderByModifiedAtDesc(postId).stream()
                .map(comment -> comment.toResponseDto()).collect(Collectors.toList());

        List<Comment> comments = commentRepository.findAllByPostIdOrderByModifiedAtDesc(post.getId());
        for (CommentResponseDto commentResponseDto : commentList) {
            String commentModifiedAt = commentResponseDto.getCommentModifiedAt();
            String year = commentModifiedAt.substring(0,4) + "년";
            String month = commentModifiedAt.substring(5,7) + "월";
            String day = commentModifiedAt.substring(8) + "일";
            String time = commentModifiedAt.substring(11,19);
            commentModifiedAt = year + month + day + time;
            commentResponseDto.setCommentModifiedAt(commentModifiedAt);
        }
        int commentCnt = comments.size();

        List<Likes> likes = likesRepository.findAllByPostId(post.getId());
        int likeCnt = likes.size();

        String postModifiedAt = post.getModifiedAt().toString();
        String year = postModifiedAt.substring(0,4) + "년";
        String month = postModifiedAt.substring(5,7) + "월";
        String day = postModifiedAt.substring(8,10) + "일";
        String time = postModifiedAt.substring(11,19);
        postModifiedAt = year + month + day + time;

        return new PostDetailResponseDto(
                post, commentCnt, likeCnt, postModifiedAt, commentList);
    }

    // 게시글 수정
    public List<Long> updatePost(
            Long postId, User user,
            PostRequestDto postRequestDto
        ){
        List<Long> imageIdList;
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글이 존재하지 않습니다."));
        if(user.getId().equals(post.getUser().getId())) {
            List<ImageIdRequestDto> imageIdRequestDtoList = postRequestDto.getImageIdList();
            List<ImageFile> imageFileList = new ArrayList<>();
            for (ImageIdRequestDto imageIdRequestDto : imageIdRequestDtoList) {
                ImageFile imageFile = imageFileRepository.findById(imageIdRequestDto.getImageId())
                        .orElseThrow(IllegalArgumentException::new);
                imageFileList.add(imageFile);
                postRequestDto.setImageFileList(imageFileList);
            }
            post.getImageFileList().clear();
            post.updatePost(postRequestDto);
            imageIdList = post.getImageFileList().stream().map(ImageFile::getId).collect(Collectors.toList());
            return imageIdList;
        } else throw new IllegalPostUpdateUserException("사용자가 작성한 게시글이 아닙니다.");
    }

    // 게시글 삭제
    public void deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글이 존재하지 않습니다."));
        if(user.getId().equals(post.getUser().getId())) {
            List<ImageFile> imageFileList = imageFileRepository.findAllByPostId(postId);
            for (ImageFile imageFile : imageFileList) {
                String deleteFileURL = imageDirName + "/" + imageFile.getConvertedFileName();
                s3Uploader.deleteFile(deleteFileURL);
                postRepository.deleteById(postId);
            }
        } else throw new IllegalPostDeleteUserException("사용자가 작성한 게시글이 아닙니다.");
    }
}
