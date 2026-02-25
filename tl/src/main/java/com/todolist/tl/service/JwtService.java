package com.todolist.tl.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    static final long EXPIRATIONTIME = 86400000;
    static final String PREFIX = "Bearer";

    static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String getToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(KEY)
                .compact(); // build가 아니라 compact로 종료
    }

    // 토큰 검증 및 username 추출
    public String getAuthUser(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null && token.startsWith(PREFIX)) {
            // "Bearer " 접두사를 제거하는 과정, 베어러 공백
            String authToken = token.substring(PREFIX.length()).trim();

            String user = Jwts.parser()
                    .verifyWith(KEY)
                    .build()
                    .parseSignedClaims(authToken)
                    .getPayload()
                    .getSubject();

            if(user != null) {
                return user;
            }
        }
        return null;
    }
}
