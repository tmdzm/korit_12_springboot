package com.korit2.cardatabase.config;

import com.korit2.cardatabase.AuthEntryPoint;
import com.korit2.cardatabase.AuthenticationFilter;
import com.korit2.cardatabase.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private UserDetailsServiceImpl userDetailsService;
    private AuthenticationFilter authenticationFilter;// 필드 추가, 여기서 출력하니까
    private AuthEntryPoint exceptionHandler;// 필드추가 2

    public void configureGlobal(AuthenticationManagerBuilder auth) throws  Exception{//전역변수용으로 사용할거다.
        auth.userDetailsService(userDetailsService);// Authentication - 인증이라는 뜻
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{ // 출 력
        //로그인 구현 전
//        http.csrf(csrf -> csrf.disable())
//                .cors(withDefaults())//이게 뭐길래 연결과 의미가
//                .authorizeHttpRequests(authorizationHttpRequests ->
//                        authorizationHttpRequests.anyRequest().permitAll());

        //로그인 구현 후
        http.csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(sessionManagement
                        -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests // 엔드 포인트임을 명시중
                    -> authorizeHttpRequests.requestMatchers(HttpMethod.POST,"/login").permitAll().anyRequest().authenticated())
                .addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling
                        -> exceptionHandling.authenticationEntryPoint(exceptionHandler));
        return  http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){//컨트롤러? 연결에 허락용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();

        source.registerCorsConfiguration("/**",config);

        return source;
    }
}
//addFilterBefore - 왜 doFilter가 아닐까, 여튼 filter가 작동하는 시점을 나타낸다