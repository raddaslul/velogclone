package com.sparta.velogclone.contoller;

import com.sparta.velogclone.dto.requestdto.LoginRequestDto;
import com.sparta.velogclone.dto.requestdto.SignupRequestDto;
import com.sparta.velogclone.dto.responsedto.CMResponseDto;
import com.sparta.velogclone.dto.responsedto.LoginResponseDto;
import com.sparta.velogclone.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api("User Controller API")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    @ApiOperation(value = "회원가입", hidden = true)
    public ResponseEntity<CMResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/user/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto,
                                                  HttpServletResponse response) {
        return userService.login(requestDto, response);
    }

    @PostMapping("/user/idCheck")
    @ApiOperation(value = "아이디 중복 체크", notes = "회원가입시 아이디 중복 여부를 체크한다.")
    public ResponseEntity<CMResponseDto> idCheck(@RequestBody @Valid SignupRequestDto requestDto) {
        return userService.idCheck(requestDto);
    }
}