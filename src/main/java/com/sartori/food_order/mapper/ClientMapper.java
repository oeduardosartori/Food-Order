package com.sartori.food_order.mapper;

import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.dto.client.ClientOutputDTO;
import com.sartori.food_order.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientInputDTO dto);
    ClientOutputDTO toOutputDTO(Client entity);
}
