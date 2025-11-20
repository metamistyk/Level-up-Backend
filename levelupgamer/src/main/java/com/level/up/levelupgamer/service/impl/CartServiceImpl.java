package com.level.up.levelupgamer.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.level.up.levelupgamer.dto.CartItemResponseDTO;
import com.level.up.levelupgamer.dto.CartResponseDTO;
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

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    private CartResponseDTO toDTO(Cart cart) {

        CartResponseDTO dto = new CartResponseDTO();

        dto.setCartId(cart.getId());

        var items = cart.getItems().stream().map(item -> {

            CartItemResponseDTO i = new CartItemResponseDTO();

            i.setId(item.getId());
            i.setProductId(item.getProduct().getId());
            i.setName(item.getProduct().getName());
            i.setImageUrl(item.getProduct().getImageUrl());
            i.setPrice(item.getProduct().getPrice());
            i.setQuantity(item.getQuantity());
            i.setSubtotal(item.getQuantity() * item.getProduct().getPrice());

            return i;

        }).collect(Collectors.toList());

        dto.setItems(items);

        double total = items.stream()
            .mapToDouble(CartItemResponseDTO::getSubtotal)
            .sum();

        dto.setTotal(total);

        return dto;
    }

    private Cart getOrCreateCart(User user) {
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        return cart;
    }

    private CartItem getExistingCartItem(Cart cart, Long productId) {
        return cart.getItems().stream()
            .filter(ci -> ci.getProduct().getId().equals(productId))
            .findFirst()
            .orElse(null);
    }

    @Override
    public CartResponseDTO getCartByUser(Long userId) {

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cart cart = getOrCreateCart(user);

        return toDTO(cart);
    }

    @Override
    @Transactional
    public CartResponseDTO addItem(Long userId, Long productId, Integer quantity) {

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cart cart = getOrCreateCart(user);

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        CartItem existing = getExistingCartItem(cart, productId);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            cartItemRepository.save(existing);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }

        return toDTO(cartRepository.findById(cart.getId()).orElseThrow());
    }

    @Override
    @Transactional
    public CartResponseDTO updateQuantity(Long cartItemId, Integer quantity) {

        CartItem item = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        if (quantity <= 0) {
            cartItemRepository.delete(item);
        } else {
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }

        Cart cart = item.getCart();
        return toDTO(cartRepository.findById(cart.getId()).orElseThrow());
    }

    @Override
    @Transactional
    public CartResponseDTO removeItem(Long cartItemId) {

        CartItem item = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        Cart cart = item.getCart();

        cartItemRepository.delete(item);

        return toDTO(cartRepository.findById(cart.getId()).orElseThrow());
    }

    @Override
    @Transactional
    public CartResponseDTO clearCart(Long userId) {

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cart cart = getOrCreateCart(user);

        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cartRepository.save(cart);

        return toDTO(cart);
    }
}
