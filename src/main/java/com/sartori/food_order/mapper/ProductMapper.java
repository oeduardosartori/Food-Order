package com.sartori.food_order.mapper;

import com.sartori.food_order.dto.product.ProductInputDTO;
import com.sartori.food_order.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductInputDTO toEntity(Product entity);
    Product toDTO(ProductInputDTO dto);
}
