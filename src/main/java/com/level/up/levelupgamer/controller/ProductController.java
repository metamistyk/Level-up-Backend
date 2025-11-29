package com.level.up.levelupgamer.controller;

import com.level.up.levelupgamer.model.Product;
import com.level.up.levelupgamer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping("/destacados")
    public List<Product> destacados() {
        return productRepository.getDestacados();
    }
}
