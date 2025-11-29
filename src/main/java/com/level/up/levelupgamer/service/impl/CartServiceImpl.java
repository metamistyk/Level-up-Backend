package com.level.up.levelupgamer.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.level.up.levelupgamer.dto.CartItemResponseDTO;
import com.level.up.levelupgamer.dto.CartResponseDTO;
import com.level.up.levelupgamer.dto.ProductResponseDTO;
import com.level.up.levelupgamer.model.Cart;
import com.level.up.levelupgamer.model.CartItem;
import com.level.up.levelupgamer.model.Product;
import com.level.up.levelupgamer.model.User;
import com.level.up.levelupgamer.repository.CartItemRepository;
import com.level.up.levelupgamer.repository.CartRepository;
import com.level.up.levelupgamer.repository.ProductRepository;
import com.level.up.levelupgamer.repository.UserRepository;
import com.level.up.levelupgamer.service.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // ============================================================
    // OBTENER CARRITO POR USUARIO
    // ============================================================
    @Override
    public CartResponseDTO getCartByUserId(Long userId) {

        // Buscar carrito del usuario
        Cart cart = cartRepository.findByUser_Id(userId);

        // Si no existe, crearlo para ese usuario
        if (cart == null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        // Obtener items del carrito desde el repositorio
        List<CartItem> items = cartItemRepository.findByCart_Id(cart.getId());

        // Mapear a DTO de respuesta
        CartResponseDTO response = new CartResponseDTO();
        response.setCartId(cart.getId());

        response.setItems(
                items.stream()
                        .map(item -> {
                            CartItemResponseDTO itemDto = new CartItemResponseDTO();
                            itemDto.setId(item.getId());
                            itemDto.setQuantity(item.getQuantity());
                            itemDto.setSubtotal(item.getQuantity() * item.getProduct().getPrice());

                            Product product = item.getProduct();
                            ProductResponseDTO productDto = new ProductResponseDTO();
                            productDto.setId(product.getId());
                            productDto.setName(product.getName());
                            productDto.setDescription(product.getDescription());
                            productDto.setPrice(product.getPrice());
                            productDto.setImageUrl(product.getImageUrl());
                            productDto.setDestacado(product.isDestacado());

                            itemDto.setProduct(productDto);

                            return itemDto;
                        })
                        .collect(Collectors.toList())
        );

        return response;
    }

    // ============================================================
    // AGREGAR PRODUCTO AL CARRITO
    // ============================================================
    @Override
    public void addToCart(Long userId, Long productId, int quantity) {

        // Buscar carrito del usuario
        Cart cart = cartRepository.findByUser_Id(userId);

        // Si el usuario no tiene carrito → crear uno asociado al usuario
        if (cart == null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        // Buscar producto
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productId));

        // ¿Ya existe ese producto en el carrito?
        CartItem existingItem =
                cartItemRepository.findByCart_IdAndProduct_Id(cart.getId(), productId);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }
    }

    // ============================================================
    // ACTUALIZAR CANTIDAD DE ITEM
    // ============================================================
    @Override
    public void updateItemQuantity(Long cartItemId, int quantity) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado con ID: " + cartItemId));

        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    // ============================================================
    // ELIMINAR ITEM DEL CARRITO
    // ============================================================
    @Override
    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    // ============================================================
    // VACIAR CARRITO COMPLETO
    // ============================================================
    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUser_Id(userId);
        if (cart == null) {
            return;
        }

        List<CartItem> items = cartItemRepository.findByCart_Id(cart.getId());
        cartItemRepository.deleteAll(items);
    }
}
