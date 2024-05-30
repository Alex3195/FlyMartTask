package org.alexkings.productdeliveryapi.repository;

import org.alexkings.productdeliveryapi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCreatedBy(Long id);
}
