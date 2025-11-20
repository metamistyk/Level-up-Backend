package com.level.up.levelupgamer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.level.up.levelupgamer.dto.ProductRequestDTO;
import com.level.up.levelupgamer.dto.ProductResponseDTO;
import com.level.up.levelupgamer.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/destacados")
    public List<ProductResponseDTO> getDestacados() {
        return productService.getDestacados();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductResponseDTO create(@RequestBody ProductRequestDTO dto) {
        return productService.create(dto);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
        return productService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/search")
    public List<ProductResponseDTO> search(@RequestParam String name) {
        return productService.search(name);
    }
}
