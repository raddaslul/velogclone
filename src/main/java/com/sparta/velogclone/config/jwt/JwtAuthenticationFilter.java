package com.sparta.velogclone.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Request의 Header에서 token값을 가져온다.
        String token = jwtAuthenticationProvider.resolveToken(request);
        //System.out.println("token = " + token);
        log.info("~~~token : " +token);
        // 토큰이 유효한지 체크한다
        if (token != null && jwtAuthenticationProvider.validateToken(token)){
            // getAuthentication 으로 인증상태(정보)를 가져와서
            Authentication authentication = jwtAuthenticationProvider.getAuthentication(token);

            // SecurityContextHolder에 저장해주어 인증상태를 유지하도록 해줌.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);    // 검증을 함

        ///////////////////   JWT 토큰 인증방식 순서   ///////////////////
        // 0. 사용자가 로그인을 하면
        // 서버에서는 계정정보를 읽어 사용자를 확인 후 사용자고유한 id값을 부여한 후
        // 서버가 클라이언트에게 JWT 발급해줌 -> 유저이름, 이메일, 토큰생성시간, 만료시간 등의 정보 포함.
        // 1. 클라이언트가 기능 수행을 위해
        // 서버에 Data(서버-클라이언트 간 주고받기로 한 약속. ex/ 글쓰기 기능에는 "title", "content", "imageUrl"이 포함)와 JWT를 묶어서 보냄.
        // 2. 서버는 클라이언트에게 받은 JWT를 doFilter로 들여와 검증함.
        // 3. Filter를 거치면서 validate되지 않고, authentication이 생성되지 않는다면 클라이언트는 기능 수행이 불가함.
        // 4. getAuthentication이 되었다면 이를 SecurityContextHolder에 저장해주어 인증상태를 유지하도록 해줌.
        // ==== UserDetailsImpl에 담기게 됨!!!
        // 5. response 결과로 클라이언트가 요청한 부분을 처리해서 그 결과를 보내줌.

        // 에러 메세지 줄 때 상태 코드 구체적으로 정해서 줘야 그게 프론트한테 더 좋을듯 하다
    }
}