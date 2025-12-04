package com.level.up.levelupgamer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Respuesta de autenticación que incluye el token JWT y los datos del usuario.
 */
@Data
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;
    private UserResponseDTO user;
}
