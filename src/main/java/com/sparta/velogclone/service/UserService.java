package com.sparta.velogclone.service;

import com.sparta.velogclone.dto.requestdto.LoginRequestDto;
import com.sparta.velogclone.dto.requestdto.SignupRequestDto;
import com.sparta.velogclone.dto.responsedto.CMResponseDto;
import com.sparta.velogclone.dto.responsedto.LoginResponseDto;
import com.sparta.velogclone.handler.ex.*;
import com.sparta.velogclone.config.jwt.JwtAuthenticationProvider;
import com.sparta.velogclone.domain.User;
import com.sparta.velogclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public ResponseEntity<LoginResponseDto> signup(SignupRequestDto requestDto, HttpServletResponse response) {

        String email = requestDto.getUserEmail(); // 아이디
        String rawPassword = requestDto.getPassword();
        String pwCheck = requestDto.getPasswordCheck();
        String userName = requestDto.getUserName();

        // 유효성 체크
        if (!isPasswordMatched(email, rawPassword))
            throw new PasswordContainsEmailException("비밀번호에 아이디가 들어갈 수 없습니다.");

        if (!isExistEmail(email))
            throw new DuplicateEmailException("이미 존재하는 아이디 입니다.");

        if (!isDuplicatePassword(rawPassword, pwCheck)) {
            throw new PasswordNotCollectException();
        }

        // 비밀번호 암호화
        String encPassword = passwordEncoder.encode(rawPassword);

        // User 객체 생성
        User user = User.builder()
                .userEmail(requestDto.getUserEmail())
                .userName(requestDto.getUserName())
                .password(encPassword)
                .build();

        // user 저장
        userRepository.save(user);

        LoginRequestDto loginRequestDto = new LoginRequestDto(email, rawPassword, userName);
        return login(loginRequestDto, response);
        //return ResponseEntity.ok(new CMResponseDto("true"));
    }

    public ResponseEntity<LoginResponseDto> login(LoginRequestDto requestDto, HttpServletResponse response) {

        User userEntity = userRepository.findByUserEmail(requestDto.getUserEmail()).orElseThrow(
                () -> new EmailNotFoundException("가입되지 않은 이메일입니다.")
        );

        if (!passwordEncoder.matches(requestDto.getPassword(), userEntity.getPassword()))
            throw new PasswordNotCollectException();


        // 토큰 정보 생성
        String token = jwtAuthenticationProvider.createToken(userEntity.getUserName(), userEntity.getUserEmail());
        //response.setHeader("X-AUTH-TOKEN", token);

        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        //Optional<User> user = userRepository.findByUserEmail(requestDto.getUserEmail());
        User user = userRepository.findByUserEmail(requestDto.getUserEmail()).orElseThrow(
                () -> new EmailNotFoundException("가입되지 않은 이메일입니다.")
        );

        return ResponseEntity.ok(new LoginResponseDto(token, requestDto.getUserEmail(), user.getUserName() ,"true"));
    }

    private boolean isDuplicatePassword(String rawPassword, String pwCheck) {
        return rawPassword.equals(pwCheck);
    }

    public boolean isExistEmail(String userEmail) {
        return !userRepository.findByUserEmail(userEmail).isPresent();
    }

    private boolean isPasswordMatched(String email, String rawPassword) {
        String domain = email.split("@")[0];
        return !rawPassword.contains(domain);
    }

    public ResponseEntity<CMResponseDto> idCheck(SignupRequestDto requestDto) {
        if(userRepository.findByUserEmail(requestDto.getUserEmail()).isPresent())
            return ResponseEntity.ok(new CMResponseDto("false"));
        return ResponseEntity.ok(new CMResponseDto("true"));
    }
}