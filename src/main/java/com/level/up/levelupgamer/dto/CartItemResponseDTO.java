package com.level.up.levelupgamer.dto;

import lombok.Data;

@Data
public class CartItemResponseDTO {

    private Long id;
    private int quantity;
    private int subtotal;
    private ProductResponseDTO product;
}
