package com.korit2.cardatabase.service;

import com.korit2.cardatabase.domain.AppUser;
import com.korit2.cardatabase.domain.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;// User.UserBuilder라고 안쓰기 위해서
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor // final 안적어도 되게한다. 근데 오ㅑ
@Service //bean 등록이 되어있는 애너테이션
public class UserDetailsServiceImpl implements UserDetailsService { //Impl - 특별한 규칙

    //field 선언
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);

        UserBuilder builder = null;
        if(user.isPresent()) {// 이하의 실행문이 실행된다면 user에 AppUser 객체가 있다는 의이
            // -> 아직 user는 Optional 자료형이기 때문
            AppUser currentUser = user.get(); // optional에서 제네릭으로 존재하는 객체를 빼내는 메서드가 .get()
            builder = User.withUsername(username); // 정적 메서드
            builder.password(currentUser.getPassword())
                    .roles(currentUser.getRole()); // AppUser에 @Data를 달아놨다 = getter가 있으니 오류가 안난다.
            //builder.roles(currentUser.getRole()); 위에 채이닝 메서드로 연결해줬다.
            //참고로 builder = 부터 이을 수 있다.
        } else {
            throw new UsernameNotFoundException("해당 username을 가진 사용자를 찾지 못했습니다.");
        }
        return builder.build();
    }

}
