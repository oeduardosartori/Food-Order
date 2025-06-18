package com.sartori.food_order.validator.client.create;

import com.sartori.food_order.dto.client.ClientInputDTO;

public interface ClientCreateValidationRule {
    void validate(ClientInputDTO dto);
}
