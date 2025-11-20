package com.level.up.levelupgamer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.level.up.levelupgamer.model.Cart;
import com.level.up.levelupgamer.model.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // Obtener carrito por usuario
    Cart findByUser(User user);
}
