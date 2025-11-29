package com.level.up.levelupgamer.controller;

import com.level.up.levelupgamer.dto.CartResponseDTO;
import com.level.up.levelupgamer.service.CartService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Agregar producto al carrito
    @PostMapping("/add")
    public void addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        cartService.addToCart(userId, productId, quantity);
    }

    // Obtener carrito por usuario
    @GetMapping("/user/{userId}")
    public CartResponseDTO getCart(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    // Actualizar cantidad de un item
    @PutMapping("/item/{cartItemId}")
    public void updateQuantity(
            @PathVariable Long cartItemId,
            @RequestParam int quantity
    ) {
        cartService.updateItemQuantity(cartItemId, quantity);
    }

    // Eliminar un producto del carrito
    @DeleteMapping("/item/{cartItemId}")
    public void removeItem(@PathVariable Long cartItemId) {
        cartService.removeItem(cartItemId);
    }

    // Vaciar carrito de un usuario
    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }
}
