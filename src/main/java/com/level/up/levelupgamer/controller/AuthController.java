package com.level.up.levelupgamer.controller;

import org.springframework.web.bind.annotation.*;

import com.level.up.levelupgamer.dto.LoginRequestDTO;
import com.level.up.levelupgamer.dto.UserRequestDTO;
import com.level.up.levelupgamer.dto.UserResponseDTO;
import com.level.up.levelupgamer.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody LoginRequestDTO dto) {
        return userService.login(dto);
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO dto) {
        return userService.register(dto);
    }
}
