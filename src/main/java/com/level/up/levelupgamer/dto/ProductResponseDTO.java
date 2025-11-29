package com.level.up.levelupgamer.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private int price;
    private String imageUrl;
    private boolean destacado;
}
