package com.sartori.food_order.service;

import com.sartori.food_order.dto.product.ProductInputDTO;
import com.sartori.food_order.dto.product.ProductOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductOutputDTO createProduct(ProductInputDTO productInputDTO);
    ProductOutputDTO getProductById(Long id);
    Page<ProductOutputDTO> getAllProducts(Pageable pageable);
    ProductOutputDTO updateProduct(Long id, ProductInputDTO productInputDTO);
    void delete(Long id);
}
