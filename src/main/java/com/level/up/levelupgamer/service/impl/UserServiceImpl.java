package com.level.up.levelupgamer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.level.up.levelupgamer.dto.LoginRequestDTO;
import com.level.up.levelupgamer.dto.UserRequestDTO;
import com.level.up.levelupgamer.dto.UserResponseDTO;
import com.level.up.levelupgamer.model.Cart;
import com.level.up.levelupgamer.model.User;
import com.level.up.levelupgamer.repository.CartRepository;
import com.level.up.levelupgamer.repository.UserRepository;
import com.level.up.levelupgamer.service.UserService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    //   CREA EL ADMIN AUTOMÁTICAMENTE SI NO EXISTE
    @PostConstruct
    public void createDefaultAdmin() {
        userRepository.findByEmail("admin@admin.com").ifPresentOrElse(
            user -> {}, // ya existe
            () -> {
                User admin = new User();
                admin.setUsername("ADMIN");
                admin.setEmail("admin@admin.com");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");  // 👈 importante

                User saved = userRepository.save(admin);

                Cart cart = new Cart();
                cart.setUser(saved);
                cartRepository.save(cart);

                System.out.println(">>> ADMIN CREADO AUTOMÁTICAMENTE <<<");
            }
        );
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public UserResponseDTO register(UserRequestDTO dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole("USER"); // 👈 siempre usuario normal

        User newUser = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(newUser);
        cartRepository.save(cart);

        return new UserResponseDTO(
            newUser.getId(),
            newUser.getUsername(),
            newUser.getEmail(),
            newUser.getRole()
        );
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO dto) {

        User u = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!u.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new UserResponseDTO(
            u.getId(),
            u.getUsername(),
            u.getEmail(),
            u.getRole()
        );
    }
}
