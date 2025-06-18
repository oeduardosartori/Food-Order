package com.sartori.food_order.validator.client.shared;

import com.sartori.food_order.config.MessageResolver;
import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.exception.BusinessException;
import com.sartori.food_order.helper.ErrorMessage;
import com.sartori.food_order.validator.client.create.ClientCreateValidationRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@Slf4j
@RequiredArgsConstructor
public class SanitizeClientDataValidator {

    private final MessageResolver messageResolver;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ ]+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\D");

    public void sanitize(ClientInputDTO dto) {
        sanitizeAndValidateName(dto);
        sanitizeAndValidateEmail(dto);
        sanitizeAndValidateCpf(dto);
    }

    private void sanitizeAndValidateName(ClientInputDTO dto) {
        String name = dto.getName() != null ? dto.getName().trim() : "";
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new BusinessException(messageResolver.getMessage(ErrorMessage.CLIENT_NAME_INVALID.code()));
        }
        dto.setName(name);
    }

    private void sanitizeAndValidateEmail(ClientInputDTO dto) {
        String email = dto.getEmail() != null ? dto.getEmail().trim().toLowerCase() : "";
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new BusinessException(messageResolver.getMessage(ErrorMessage.CLIENT_EMAIL_INVALID.code()));
        }
        dto.setEmail(email);
    }

    private void sanitizeAndValidateCpf(ClientInputDTO dto) {
        String cpf = dto.getCpf() != null ? NUMERIC_PATTERN.matcher(dto.getCpf()).replaceAll("") : "";
        if (cpf.length() != 11) {
            throw new BusinessException(messageResolver.getMessage(ErrorMessage.CLIENT_CPF_INVALID.code()));
        }
        dto.setCpf(cpf);
    }
}
