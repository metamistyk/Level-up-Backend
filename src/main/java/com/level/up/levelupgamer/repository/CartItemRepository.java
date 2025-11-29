package com.level.up.levelupgamer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.level.up.levelupgamer.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Todos los items de un carrito
    List<CartItem> findByCart_Id(Long cartId);

    // Un item específico por carrito + producto
    CartItem findByCart_IdAndProduct_Id(Long cartId, Long productId);
}
