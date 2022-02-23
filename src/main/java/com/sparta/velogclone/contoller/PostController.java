package com.sparta.velogclone.contoller;

import com.sparta.velogclone.config.auth.UserDetailsImpl;
import com.sparta.velogclone.domain.ImageFile;
import com.sparta.velogclone.domain.Post;
import com.sparta.velogclone.domain.User;
import com.sparta.velogclone.dto.requestdto.ImageIdRequestDto;
import com.sparta.velogclone.dto.requestdto.PostRequestDto;
import com.sparta.velogclone.dto.responsedto.ImageFileResponseDto;
import com.sparta.velogclone.dto.responsedto.PostDetailResponseDto;
import com.sparta.velogclone.dto.responsedto.PostResponseDto;
import com.sparta.velogclone.handler.ex.LoginUserNotFoundException;
import com.sparta.velogclone.service.ImageFileService;
import com.sparta.velogclone.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostController {

    private final PostService postService;
    private final ImageFileService imageFileService;

    // 이미지 업로드
    @PostMapping("/api/upload")
    @ApiOperation(value = "이미지 업로드", notes = "게시글 등록 할 때 이미지 업로드")
    public ImageFileResponseDto uploadImage(
            @RequestPart("imageFile")MultipartFile multipartFile,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws IOException {
        if(userDetails != null) {
            ImageFile imageFile = imageFileService.uploadFile(multipartFile);
            return new ImageFileResponseDto(imageFile.getFilePath(), imageFile.getId());
        }   else throw new LoginUserNotFoundException("로그인한 유저 정보가 없습니다.");
    }

    // 게시글 작성
    @PostMapping("/api/posting")
    @ApiOperation(value = "게시물 등록", notes = "게시물에 이미지 파일을 첨부해서 등록한다")
    public List<String> savePost(
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        if(userDetails != null) {
            List<String> filePathList = new ArrayList<>();
            User user = userDetails.getUser();
            List<ImageFile> imageFileList = postService.savePost(postRequestDto, user);
            String filePath;
            for (ImageFile imageFile : imageFileList) {
                 filePath = imageFile.getFilePath();
                 filePathList.add(filePath);
            } return filePathList;
        } else throw new LoginUserNotFoundException("로그인한 유저 정보가 없습니다.");
    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    @GetMapping("/api/posting")
    public List<PostResponseDto> viewPost() {
        return postService.viewPost();
    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    @GetMapping("/api/posting/{postId}")
    public PostDetailResponseDto viewPostDetail(@PathVariable Long postId) {
        return postService.viewPostDetail(postId);
    }

    // 게시글 수정
    @PutMapping("/api/posting/{postId}")
    public List<Long> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails != null) {
            User user = userDetails.getUser();
            return postService.updatePost(postId, user, postRequestDto);
        } else throw new LoginUserNotFoundException("로그인한 유저 정보가 없습니다.");
    }

    // 게시글 삭제
    @DeleteMapping("/api/posting/{postId}")
    public HashMap<String, Object> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails != null) {
            User user = userDetails.getUser();
            postService.deletePost(postId, user);
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "true");
            return result;
        } else throw new LoginUserNotFoundException("로그인한 유저 정보가 없습니다.");
    }
}
