package com.level.up.levelupgamer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart_item")
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    // muchos items pertenecen a UN carrito
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // muchos items pertenecen a UN producto
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
