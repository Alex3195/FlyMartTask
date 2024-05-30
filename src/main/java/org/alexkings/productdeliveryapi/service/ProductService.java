package org.alexkings.productdeliveryapi.service;

import org.alexkings.productdeliveryapi.model.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);

    ProductDto update(Long id, ProductDto productDto);

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    void deleteById(Long id);

    List<ProductDto> findByUserName(String username);
}
