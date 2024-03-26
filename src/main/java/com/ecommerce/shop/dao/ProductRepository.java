package com.ecommerce.shop.dao;

import com.ecommerce.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

    Page<Product> findByActive(@Param("active") Boolean active, Pageable pageable);

    Page<Product> findByUnitPrice(@Param("unitPrice") Double unitPrice, Pageable pageable);
}