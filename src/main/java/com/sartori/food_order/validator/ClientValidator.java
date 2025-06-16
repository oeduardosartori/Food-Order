package com.sartori.food_order.validator;

import com.sartori.food_order.entity.Client;
import com.sartori.food_order.exception.BusinessException;
import com.sartori.food_order.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private final ClientRepository clientRepository;

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
            return "Email already registered";
        }
        if (clientFound.getCpf().equals(cpf)){
            return "CPF already registered";
        }
        return "Duplicate Data";
    }
}
