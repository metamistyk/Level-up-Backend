package com.level.up.levelupgamer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.level.up.levelupgamer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Para buscar por email en login
    User findByEmail(String email);
}
