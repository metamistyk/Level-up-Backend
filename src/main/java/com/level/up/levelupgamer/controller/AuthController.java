package com.level.up.levelupgamer.controller;

import org.springframework.web.bind.annotation.*;

import com.level.up.levelupgamer.dto.AuthResponseDTO;
import com.level.up.levelupgamer.dto.LoginRequestDTO;
import com.level.up.levelupgamer.dto.UserRequestDTO;
import com.level.up.levelupgamer.dto.UserResponseDTO;
import com.level.up.levelupgamer.security.JwtUtil;
import com.level.up.levelupgamer.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    /**
     * Login ORIGINAL que ya usa tu frontend.
     * Lo dejamos igual para no romper nada.
     */
    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody LoginRequestDTO dto) {
        return userService.login(dto);
    }

    /**
     * Nuevo login que incluye token JWT.
     * El frontend nuevo usará ESTE endpoint: /api/auth/login-jwt
     */
    @PostMapping("/login-jwt")
    public AuthResponseDTO loginWithJwt(@RequestBody LoginRequestDTO dto) {
        UserResponseDTO user = userService.login(dto);
        String token = jwtUtil.generateToken(user);
        return new AuthResponseDTO(token, user);
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO dto) {
        return userService.register(dto);
    }
}
