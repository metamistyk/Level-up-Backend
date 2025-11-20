package com.level.up.levelupgamer.dto;

import java.util.List;
import lombok.Data;

@Data
public class CartResponseDTO {

    private Long cartId;
    private List<CartItemResponseDTO> items;
    private Double total;
}
