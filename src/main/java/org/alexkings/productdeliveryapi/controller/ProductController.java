package org.alexkings.productdeliveryapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.alexkings.productdeliveryapi.model.ProductDto;
import org.alexkings.productdeliveryapi.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {
        return ResponseEntity.ok(productService.save(productDto));
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/getUserProducts")
    public ResponseEntity<List<ProductDto>> getUserProducts(HttpServletRequest request, HttpServletResponse response) {
        UserDetails u = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(productService.findByUserName(u.getUsername()));
    }

    @GetMapping("/products")
    @PreAuthorize("hasPermission('PRODUCT_READ') and hasRole('ADMIN')")
    public ResponseEntity<List<ProductDto>> getProducts(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(productService.findAll());
    }
}
