package com.sartori.food_order.validator.client.update;

import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.validator.client.shared.SanitizeClientDataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SanitizeValidationClientUpdate implements ClientUpdateValidationRule {

    private final SanitizeClientDataValidator sanitizeClientDataValidator;

    @Override
    public void validate(ClientInputDTO dto, Long id) {
        sanitizeClientDataValidator.sanitize(dto);
    }
}

