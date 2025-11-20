package com.level.up.levelupgamer.dto;

import lombok.Data;

@Data
public class CartItemResponseDTO {

    private Long id;
    private Long productId;
    private String name;
    private String imageUrl;
    private Double price;
    private Integer quantity;
    private Double subtotal;
}
