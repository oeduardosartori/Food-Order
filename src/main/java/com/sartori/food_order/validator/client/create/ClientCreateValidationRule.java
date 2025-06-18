package com.sartori.food_order.validator.client.rules;

import com.sartori.food_order.dto.client.ClientInputDTO;

public interface ClientCreateValidationRule {
    void validate(ClientInputDTO dto);
}
