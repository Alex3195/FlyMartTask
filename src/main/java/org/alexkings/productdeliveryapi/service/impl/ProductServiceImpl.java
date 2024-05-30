package org.alexkings.productdeliveryapi.service.impl;

import org.alexkings.productdeliveryapi.entities.Product;
import org.alexkings.productdeliveryapi.entities.Users;
import org.alexkings.productdeliveryapi.model.ProductDto;
import org.alexkings.productdeliveryapi.repository.ProductRepository;
import org.alexkings.productdeliveryapi.repository.UserRepository;
import org.alexkings.productdeliveryapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = dtoToEntity(productDto);
        product = productRepository.save(product);
        if (product != null) return entityToDto(product);
        return null;
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Product e = productRepository.getReferenceById(id);
        if (e != null) {
            e.setName(productDto.getName());
            e.setImage(productDto.getImage());
            e.setPrice(productDto.getPrice());
            e = productRepository.save(e);
            return entityToDto(e);
        }
        return null;
    }

    @Override
    public ProductDto findById(Long id) {
        Product e = productRepository.getReferenceById(id);
        if (e != null) return entityToDto(e);
        return null;
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) return null;
        return products.stream().map(this::entityToDto).toList();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> findByUserName(String username) {
        Users u = userRepository.findByUsername(username).orElse(null);
        if (u == null) return List.of();

        List<Product> products = productRepository.findByCreatedBy(u.getId());
        if (products.isEmpty()) return null;
        return products.stream().map(this::entityToDto).toList();
    }

    private ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        return productDto;
    }

    private Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        return product;
    }
}
