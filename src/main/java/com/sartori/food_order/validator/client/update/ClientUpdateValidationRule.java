package com.sartori.food_order.validator.client.update;

import com.sartori.food_order.dto.client.ClientInputDTO;

public interface ClientUpdateValidationRule {
    void validate(ClientInputDTO dto, Long id);
}
