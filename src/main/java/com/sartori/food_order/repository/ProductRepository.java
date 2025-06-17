package com.sartori.food_order.repository;

import com.sartori.food_order.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameIgnoreCase(String name);
    Optional<Product> findByNameIgnoreCase(String name);
}
