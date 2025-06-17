package com.sartori.food_order.mapper;

import com.sartori.food_order.dto.orderitem.OrderItemInputDTO;
import com.sartori.food_order.dto.orderitem.OrderItemOutputDTO;
import com.sartori.food_order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "product", ignore = true)
    OrderItem toEntity(OrderItemInputDTO dto);

    @Mapping(source = "product.id", target = "productId")
    OrderItemOutputDTO toOutputDTO(OrderItem entity);
}

