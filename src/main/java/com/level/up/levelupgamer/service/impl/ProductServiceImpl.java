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

    private ProductResponseDTO toDTO(Product p) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setPrice(p.getPrice());          // ✔ ahora matchea con int
        dto.setImageUrl(p.getImageUrl());
        dto.setDestacado(p.isDestacado());   // ✔ boolean → boolean
        return dto;
    }

    private Product fromDTO(ProductRequestDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setImageUrl(dto.getImageUrl());
        p.setDestacado(dto.isDestacado());
        return p;
    }

    @Override
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDTO> getDestacados() {
        return productRepository.getDestacados()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getById(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toDTO(p);
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO dto) {
        Product p = fromDTO(dto);
        return toDTO(productRepository.save(p));
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setImageUrl(dto.getImageUrl());
        p.setDestacado(dto.isDestacado());

        return toDTO(productRepository.save(p));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDTO> search(String name) {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }
}
