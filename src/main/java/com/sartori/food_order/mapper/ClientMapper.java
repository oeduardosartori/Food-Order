package com.sartori.food_order.mapper;

import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientInputDTO dto);
    ClientInputDTO toDTO(Client entity);
}
