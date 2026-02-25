package com.todolist.tl.service;

import com.todolist.tl.domain.User;
import com.todolist.tl.domain.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if(user.isPresent()) {
            User currentUser = user.get();
            builder = org.springframework.security.core.userdetails.User
                    .withUsername(username); // 이건 좀 어지럽네, 전부 부르기
            builder.password(currentUser.getPassword())
                    .roles(currentUser.getRole());
            //참고로 builder = 부터 이을 수 있다.
        } else {
            throw new UsernameNotFoundException("해당 username을 가진 사용자를 찾지 못했습니다.");
        }
        return builder.build();
    }
}
