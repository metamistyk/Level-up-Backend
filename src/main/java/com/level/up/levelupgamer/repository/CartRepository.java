package com.level.up.levelupgamer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.level.up.levelupgamer.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // Buscar carrito por id de usuario (campo user.id en la entidad Cart)
    Cart findByUser_Id(Long userId);
}
