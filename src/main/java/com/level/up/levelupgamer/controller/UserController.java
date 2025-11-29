package com.level.up.levelupgamer.controller;

import com.level.up.levelupgamer.model.User;
import com.level.up.levelupgamer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET all
    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // POST crear
    @PostMapping
    public User create(@RequestBody User u) {
        return userRepository.save(u);
    }

    // PUT actualizar
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User u) {
        User original = userRepository.findById(id).orElse(null);
        if (original == null) return null;

        original.setUsername(u.getUsername());
        original.setEmail(u.getEmail());

        return userRepository.save(original);
    }

    // DELETE eliminar
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
