package com.level.up.levelupgamer.service;

import com.level.up.levelupgamer.dto.CartResponseDTO;

public interface CartService {

    // Obtener el carrito de un usuario
    CartResponseDTO getCartByUserId(Long userId);

    // Agregar producto al carrito
    void addToCart(Long userId, Long productId, int quantity);

    // Actualizar cantidad de un item
    void updateItemQuantity(Long cartItemId, int quantity);

    // Eliminar un item del carrito
    void removeItem(Long cartItemId);

    // Vaciar el carrito completo
    void clearCart(Long userId);
}
