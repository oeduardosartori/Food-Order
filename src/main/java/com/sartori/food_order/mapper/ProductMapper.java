package com.sartori.food_order.mapper;

import com.sartori.food_order.dto.product.ProductInputDTO;
import com.sartori.food_order.dto.product.ProductOutputDTO;
import com.sartori.food_order.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductInputDTO dto);
    ProductOutputDTO toOutputDTO(Product entity);
}
