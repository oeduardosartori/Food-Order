package com.sartori.food_order.validator.client.create;

import com.sartori.food_order.config.MessageResolver;
import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.exception.BusinessException;
import com.sartori.food_order.helper.ErrorMessage;
import com.sartori.food_order.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CpfEmailUniquenessCreateValidator implements ClientCreateValidationRule {

    private final ClientRepository clientRepository;
    private final MessageResolver messageResolver;

    @Override
    public void validate(ClientInputDTO dto) {
        uniqueCpfCreateValidation(dto);
        uniqueEmailCreateValidation(dto);
    }

    private void uniqueCpfCreateValidation(ClientInputDTO dto) {
        clientRepository.findByCpf(dto.getCpf())
                .ifPresent(existing -> {
                    throw new BusinessException(messageResolver.getMessage(
                            ErrorMessage.CLIENT_CPF_DUPLICATE.code()));
                });
    }

    private void uniqueEmailCreateValidation(ClientInputDTO dto) {
        clientRepository.findByEmail(dto.getEmail())
                .ifPresent(existing -> {
                    throw new BusinessException(messageResolver.getMessage(
                            ErrorMessage.CLIENT_EMAIL_DUPLICATE.code()));
                });
    }
}
