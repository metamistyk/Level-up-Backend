package com.level.up.levelupgamer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.level.up.levelupgamer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);
}
