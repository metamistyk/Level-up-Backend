package com.level.up.levelupgamer.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "product")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;
    @Column(nullable = false, length = 40)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false, length = 40)
    private Double price;
    @Column(nullable = false, length = 40)
    private Integer stock;
    @Column(nullable = false, length = 40)
    private String imageUrl;
    @Column(nullable = false, length = 40)
    private Boolean destacado;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

}
