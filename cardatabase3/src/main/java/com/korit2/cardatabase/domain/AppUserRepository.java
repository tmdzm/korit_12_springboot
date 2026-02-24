package com.korit2.cardatabase.domain;

import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false) // 다른곳에 방출시키지 않겠다
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String username);

}

