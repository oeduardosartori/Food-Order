package com.sartori.food_order.validator;

import com.sartori.food_order.config.MessageResolver;
import com.sartori.food_order.entity.Client;
import com.sartori.food_order.exception.BusinessException;
import com.sartori.food_order.helper.ErrorMessage;
import com.sartori.food_order.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private final ClientRepository clientRepository;
    private final MessageResolver messageResolver;

    /**
     * Validates unique email and CPF, ignoring the ID provided (null for create).
     */
    public void validateEmailAndCpfUniqueness(String email, String cpf, Long id) {
        var existingClient = clientRepository.findByEmailOrCpf(email, cpf);

        if (existingClient.isPresent()) {
            Client clientFound = existingClient.get();

            if (id == null || !clientFound.getId().equals(id)) {
                throw new BusinessException(getDuplicateFieldMessage(clientFound, email, cpf));
            }
        }
    }

    /**
     * Overhead for create (ignoreId == null).
     */
    public void validateEmailAndCpfUniqueness(String email, String cpf) {
        validateEmailAndCpfUniqueness(email, cpf, null);
    }

    private String getDuplicateFieldMessage(Client clientFound, String email, String cpf) {
        if (clientFound.getEmail().equals(email)) {
            return messageResolver.getMessage(ErrorMessage.CLIENT_EMAIL_DUPLICATE.code());
        }
        if (clientFound.getCpf().equals(cpf)){
            return messageResolver.getMessage(ErrorMessage.CLIENT_CPF_DUPLICATE.code());
        }
        throw new IllegalStateException(
                messageResolver.getMessage(ErrorMessage.INCONSISTENT_VALIDATION.code())
        );
    }
}
