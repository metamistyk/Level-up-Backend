package com.level.up.levelupgamer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.level.up.levelupgamer.model.CartItem;
import com.level.up.levelupgamer.model.Cart;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Obtener items de un carrito
    List<CartItem> findByCart(Cart cart);
}
