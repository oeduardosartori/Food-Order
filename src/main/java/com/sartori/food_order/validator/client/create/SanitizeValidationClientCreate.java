package com.sartori.food_order.validator.client.create;

import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.validator.client.shared.SanitizeClientDataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SanitizeValidationClientCreate implements ClientCreateValidationRule{

    private final SanitizeClientDataValidator sanitizeClientDataValidator;

    @Override
    public void validate(ClientInputDTO dto) {
        sanitizeClientDataValidator.sanitize(dto);
    }
}
