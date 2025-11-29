package com.level.up.levelupgamer.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // id autoincremental

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private int price;

    // 👇 OJO AQUÍ: forzamos el nombre EXACTO de la columna
    @Column(name = "imageUrl", nullable = false, length = 100)
    private String imageUrl;

    @Column(nullable = false)
    private boolean destacado;
}
