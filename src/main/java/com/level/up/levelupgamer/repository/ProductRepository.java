package com.level.up.levelupgamer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.level.up.levelupgamer.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Buscar destacados
    List<Product> findByDestacado(boolean destacado);

    // Buscar por nombre (usado en ProductServiceImpl)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Endpoint personalizado /destacados
    @Query("SELECT p FROM Product p WHERE p.destacado = true")
    List<Product> getDestacados();
}
