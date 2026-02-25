package com.korit2.cardatabase.web;

import com.korit2.cardatabase.domain.AccountCredentials;
import com.korit2.cardatabase.service.JwtService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor // 엔티티 클래스가 아니라서 기본생성자 - NoArgs가 필요없다.
public class LoginController {
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    //로그인이니까 POST 요청이어야 한다.
    @PostMapping("/login") // POST 요청명시
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials){
        // 여기에 토큰 생성하고 응답의 Authorization 헤더로 전송해주는 로직을 작성할 예정
        //spring data REST를 적용했지만 , 뭐가 없다고?
        //위의 한줄에 요청과 응답이 동시에 있다.
        //호출을 할땐 요청, return할때는 응답.
        //정의와 호출은 다르다.
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
        //credentials는 AccountCredentials를 객체로 만든것
        Authentication auth = authenticationManager.authenticate(creds); // ~~Manager는 위에서 객체로 받았다.

        //토큰 생성하는 JwtService의 getToken() 메서드를 호출하여 그 결과값을 대입 -> 지역 변수로 쓰인다.
        String jwts = jwtService.getToken(auth.getName());//이건 Java 내에서만 만들어진거지 외부에선 볼 수 없다.
        // 그렇다면 private...으로 선언한것과 뭐가 다른가 private은 그 클래스 내에서만 쓸 수 있다.
        // 이건 프로젝트라서...? 뭔가 뭔가 뭔가 한데 뭘 이해를 못하고 있지

        //34번 라인의 결과 jwt가 String 자료형으로 저장 되었다. 이를 headers의 authorizaion부분에 업로드해줘야 한다.
        //헤더에 탑제하는 과정
        return ResponseEntity.ok() // ok라는 정적메서드 호출
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,"Authorization")
                .build();
    }
}
