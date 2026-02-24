package com.korit2.cardatabase.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false) // @NOnNull 과 nullable = false의 차이? not null은 스프링부트에서, nullable은 DB까지 가서 막힌다.
    private Long id;

    @Column(nullable = false,unique = true)
    private  String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    public AppUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
