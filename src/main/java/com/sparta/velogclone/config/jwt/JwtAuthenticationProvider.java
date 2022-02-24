package com.sparta.velogclone.config.jwt;

import com.sparta.velogclone.config.auth.UserDetailsImplService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider {

    private String secretKey = "sparta";

    private Long tokenValidTime = 1000L * 60 * 120; // 2시간

    private final UserDetailsImplService userDetailsImplService;

    // JWT 토큰 생성
    public String createToken(String userPk, String userEmail) {
        /*
        등록된 클레임 ( Claims를 Ctrl 클릭해서 가면 있다. ) - setAudience
        - 토큰 정보를 표현하기 위해 이미 정해진 종류의 데이터들로 모두 선택적으로 작성이 가능하며 사용할 것을 권장한다.
        공개 클레임 (Public Claim) -> 여기서 확인이 가능한지 모르겠네
        - 사용자 정의 클레임으로 공개용 정보를 위해 사용되며 충돌 방지를 위해 URI 포맷을 이용합니다.
        비공개 클레임 (Private Claim) - 아래에서 저장한 username과 userEmail
        - 사용자 정의 클레임으로 서버와 클라이언트 사이에 임의로 지정한 정보를 저장합니다.
        */

        // 비공개 클레임(사용자가 정의한 클레임으로 서버와 클라이언트 사이에 임의로 지정한 정보)을 payload 정보에 저장
        // payload 에는 토큰에 담을 Claim 정보를 포함하고 있다.
        Claims claims = Jwts.claims().setSubject(userPk); // payload에  정보 저장
        claims.put("username", userPk); // 정보를 저장할 데이터 넣어주기
        claims.put("userEmail",userEmail);

        Date now = new Date();
        // Jwt 인터페이스의 인스턴스를 만드는 클래스
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                //.setAudience("bob")   // 이런식으로 등록된 claim 사용가능
                // 사용할 암호화 알고리즘 및 signature에 들어갈 secretKey 값 설정
                .compact();
    }

    // 토큰에서 회원 정보 추출
    public String getUserEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userEmail").toString();
    } // 추출하면 username, email등 유저의 정보가 나오게 됨.

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsImplService.loadUserByUsername(this.getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Request의 Header에서 token 값을 가져온다. 헤더에 "Authorization" : "TOKEN값" 형식으로 있다.
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 + 만료일자 확인  // -> 토큰이 expire되지 않았는지 True/False로 반환해줌.
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            //System.out.println(claims); // JWT 토큰(클라이언트에서 보낸)이 잘 들어오는지 검증하는 부분 -> 서버 콘솔에 token 찍힘.
            return !claims.getBody().getExpiration().before(new Date()); // expire시간이 되지 않았다면 True!
        } catch (Exception e) {
            return false;
        }
    }
}