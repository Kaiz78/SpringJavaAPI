package com.example.SpringAPI.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    // SELECT * FROM product WHERE id = ?
    @Query("SELECT p FROM Product p WHERE p.sku = ?1")
    Optional<Product> findProductBySku(String sku);

    // INSERT MULTIPLE PRODUCTS



}
