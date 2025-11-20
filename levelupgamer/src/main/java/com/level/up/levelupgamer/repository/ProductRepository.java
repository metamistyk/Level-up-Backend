package com.level.up.levelupgamer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.level.up.levelupgamer.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Para mostrar productos destacados
    List<Product> findByDestacadoTrue();
}
