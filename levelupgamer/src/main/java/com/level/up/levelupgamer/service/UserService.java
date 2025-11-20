package com.level.up.levelupgamer.service;

import com.level.up.levelupgamer.dto.LoginRequestDTO;
import com.level.up.levelupgamer.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO login(LoginRequestDTO dto);

}
