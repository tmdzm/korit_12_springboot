package com.temp.springbootstudy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NonNull;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동생성
    private Long id;

    @NonNull //nonNull인 이유? notNull이 아닌 이유
    private String name , username , password;

    public AppUser() {}//왜 필수일까

    public AppUser(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
