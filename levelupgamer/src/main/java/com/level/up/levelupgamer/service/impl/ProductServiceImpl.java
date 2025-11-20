package com.level.up.levelupgamer.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.level.up.levelupgamer.dto.ProductRequestDTO;
import com.level.up.levelupgamer.dto.ProductResponseDTO;
import com.level.up.levelupgamer.model.Product;
import com.level.up.levelupgamer.repository.ProductRepository;
import com.level.up.levelupgamer.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private ProductResponseDTO toDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setImageUrl(product.getImageUrl());
        dto.setDestacado(product.getDestacado());
        return dto;
    }

    private Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setImageUrl(dto.getImageUrl());
        product.setDestacado(dto.getDestacado());
        return product;
    }

    @Override
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDTO> getDestacados() {
        return productRepository.findByDestacadoTrue()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toDTO(product);
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO dto) {
        Product product = toEntity(dto);
        return toDTO(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product existing = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setStock(dto.getStock());
        existing.setImageUrl(dto.getImageUrl());
        existing.setDestacado(dto.getDestacado());

        return toDTO(productRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDTO> search(String name) {
        return productRepository.findAll()
            .stream()
            .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}
