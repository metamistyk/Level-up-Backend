package com.level.up.levelupgamer.service.impl;

import org.springframework.stereotype.Service;

import com.level.up.levelupgamer.dto.LoginRequestDTO;
import com.level.up.levelupgamer.dto.UserResponseDTO;
import com.level.up.levelupgamer.model.User;
import com.level.up.levelupgamer.repository.UserRepository;
import com.level.up.levelupgamer.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO login(LoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail());

        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Contraseña en texto plano (modo simple)
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        return response;
    }
}
