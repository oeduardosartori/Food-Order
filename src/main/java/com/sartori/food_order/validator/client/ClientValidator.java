package com.sartori.food_order.validator.client;

import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.validator.client.create.ClientCreateValidationRule;
import com.sartori.food_order.validator.client.update.ClientUpdateValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private final List<ClientCreateValidationRule> createRules;
    private final List<ClientUpdateValidationRule> updateRules;

    public void validateCreateClient(ClientInputDTO dto) {
        createRules.forEach(rule -> rule.validate(dto));
    }

    public void validateUpdateClient(ClientInputDTO dto, Long clientId) {
        updateRules.forEach(rule -> rule.validate(dto, clientId));
    }
}
