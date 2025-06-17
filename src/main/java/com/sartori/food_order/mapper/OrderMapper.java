package com.sartori.food_order.mapper;

import com.sartori.food_order.dto.order.OrderInputDTO;
import com.sartori.food_order.dto.order.OrderOutputDTO;
import com.sartori.food_order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(target = "client", ignore = true)
    Order toEntity(OrderInputDTO dto);

    @Mapping(source = "client.id", target = "clientId")
    OrderOutputDTO toOutputDTO(Order entity);
}
