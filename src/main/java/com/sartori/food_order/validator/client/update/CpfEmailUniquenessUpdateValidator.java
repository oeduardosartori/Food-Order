package com.sartori.food_order.validator.client.update;

import com.sartori.food_order.config.MessageResolver;
import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.exception.BusinessException;
import com.sartori.food_order.helper.ErrorMessage;
import com.sartori.food_order.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CpfEmailUniquenessUpdateValidator implements ClientUpdateValidationRule {

    private final ClientRepository clientRepository;
    private final MessageResolver messageResolver;

    @Override
    public void validate(ClientInputDTO dto, Long clientId) {
        uniqueCpfUpdateValidation(dto, clientId);
        uniqueEmailUpdateValidation(dto, clientId);
    }

    private void uniqueCpfUpdateValidation(ClientInputDTO dto, Long clientId) {
        clientRepository.findByCpf(dto.getCpf())
                .filter(existing -> !existing.getId().equals(clientId))
                .ifPresent(existing -> {
                    throw new BusinessException(messageResolver.getMessage(
                            ErrorMessage.CLIENT_CPF_DUPLICATE.code()));
                });
    }

    private void uniqueEmailUpdateValidation(ClientInputDTO dto, Long clientId) {
        clientRepository.findByEmail(dto.getEmail())
                .filter(existing -> !existing.getId().equals(clientId))
                .ifPresent(existing -> {
                    throw new BusinessException(messageResolver.getMessage(
                            ErrorMessage.CLIENT_EMAIL_DUPLICATE.code()));
                });
    }
}
