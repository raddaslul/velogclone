package com.sparta.velogclone.contoller;

import com.sparta.velogclone.config.auth.UserDetailsImpl;
import com.sparta.velogclone.domain.User;
import com.sparta.velogclone.dto.requestdto.PostRequestDto;
import com.sparta.velogclone.dto.responsedto.PostDetailResponseDto;
import com.sparta.velogclone.dto.responsedto.PostResponseDto;
import com.sparta.velogclone.handler.ex.LoginUserNotFoundException;
import com.sparta.velogclone.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
@Slf4j
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posting")
    @ApiOperation(value = "게시물 등록", notes = "게시물에 이미지 파일을 첨부해서 등록한다")
    public HashMap<String, Object> savePost(
            @RequestPart("imageFile") MultipartFile multipartFile,
            @RequestPart("post") PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) throws IOException {
        log.info("~~~ multipartFile : "+ multipartFile.getName());
        if(userDetails != null) {
            User user = userDetails.getUser();
            postService.savePost(multipartFile, postRequestDto, user);
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "true");
            return result;
        } else throw new LoginUserNotFoundException("로그인한 유저 정보가 없습니다.");
    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    @GetMapping("/posting")
    public List<PostResponseDto> viewPost() {
        return postService.viewPost();
    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    @GetMapping("/posting/{postId}")
    public PostDetailResponseDto viewPostDetail(@PathVariable Long postId) {
        return postService.viewPostDetail(postId);
    }

    // 게시글 삭제
//    @DeleteMapping("/api/posting/{postId}")
//    public HashMap<String, Object> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        if(userDetails.getUser().getId().equals(postId)) {
//            postService.deletePost(postId, userDetails);
//            HashMap<String, Object> result = new HashMap<>();
//            result.put("result", "true");
//            return result;
//        } else throw new LoginUserNotFoundException("로그인한 유저 정보가 없습니다.");
//    }
}
