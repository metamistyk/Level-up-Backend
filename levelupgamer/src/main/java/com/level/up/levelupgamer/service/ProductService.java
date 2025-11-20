package com.level.up.levelupgamer.service;

import java.util.List;
import com.level.up.levelupgamer.dto.ProductRequestDTO;
import com.level.up.levelupgamer.dto.ProductResponseDTO;

public interface ProductService {

    List<ProductResponseDTO> getAll();
    List<ProductResponseDTO> getDestacados();
    ProductResponseDTO getById(Long id);
    ProductResponseDTO create(ProductRequestDTO dto);
    ProductResponseDTO update(Long id, ProductRequestDTO dto);
    void delete(Long id);
    List<ProductResponseDTO> search(String name);
}
