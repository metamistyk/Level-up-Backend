package com.level.up.levelupgamer.service;

import com.level.up.levelupgamer.dto.CartResponseDTO;

public interface CartService {

    CartResponseDTO getCartByUser(Long userId);

    CartResponseDTO addItem(Long userId, Long productId, Integer quantity);

    CartResponseDTO updateQuantity(Long cartItemId, Integer quantity);

    CartResponseDTO removeItem(Long cartItemId);

    CartResponseDTO clearCart(Long userId);
}
