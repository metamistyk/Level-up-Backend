package com.level.up.levelupgamer.service;

import java.util.List;

import com.level.up.levelupgamer.dto.LoginRequestDTO;
import com.level.up.levelupgamer.dto.UserRequestDTO;
import com.level.up.levelupgamer.dto.UserResponseDTO;
import com.level.up.levelupgamer.model.User;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
    UserResponseDTO register(UserRequestDTO dto);
    void delete(Long id);

    // Login
    UserResponseDTO login(LoginRequestDTO dto);
}
