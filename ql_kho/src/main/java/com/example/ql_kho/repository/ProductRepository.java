package com.example.ql_kho.repository;

import com.example.ql_kho.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
