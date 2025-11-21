package com.level.up.levelupgamer.service;

import java.util.List;

import com.level.up.levelupgamer.dto.LoginRequestDTO;
import com.level.up.levelupgamer.dto.UserResponseDTO;
import com.level.up.levelupgamer.model.User;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void delete(Long id);

    // LOGIN
    UserResponseDTO login(LoginRequestDTO dto);
}
