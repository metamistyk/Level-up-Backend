package com.level.up.levelupgamer.controller;

import org.springframework.web.bind.annotation.*;

import com.level.up.levelupgamer.dto.CartResponseDTO;
import com.level.up.levelupgamer.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    @GetMapping("/user/{userId}")
    public CartResponseDTO getCart(@PathVariable Long userId) {
        return cartService.getCartByUser(userId);
    }

    @PostMapping("/add")
    public CartResponseDTO addItem(
        @RequestParam Long userId,
        @RequestParam Long productId,
        @RequestParam Integer quantity
    ) {
        return cartService.addItem(userId, productId, quantity);
    }

    @PutMapping("/item/{cartItemId}")
    public CartResponseDTO updateQuantity(
        @PathVariable Long cartItemId,
        @RequestParam Integer quantity
    ) {
        return cartService.updateQuantity(cartItemId, quantity);
    }

    @DeleteMapping("/item/{cartItemId}")
    public CartResponseDTO removeItem(@PathVariable Long cartItemId) {
        return cartService.removeItem(cartItemId);
    }

    @DeleteMapping("/clear/{userId}")
    public CartResponseDTO clearCart(@PathVariable Long userId) {
        return cartService.clearCart(userId);
    }
}
