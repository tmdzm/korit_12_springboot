package com.todolist.tl.controller;

import com.todolist.tl.domain.AccountCredentials;
import com.todolist.tl.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/login") // POST 요청명시
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
        //credentials는 AccountCredentials를 객체로 만든것
        Authentication auth = authenticationManager.authenticate(creds); // ~~Manager는 위에서 객체로 받았다.

        //토큰 생성하는 JwtService의 getToken() 메서드를 호출하여 그 결과값을 대입 -> 지역 변수로 쓰인다.
        String jwts = jwtService.getToken(auth.getName());//이건 Java 내에서만 만들어진거지 외부에선 볼 수 없다.

        return ResponseEntity.ok() // ok라는 정적메서드 호출
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();
    }
}
