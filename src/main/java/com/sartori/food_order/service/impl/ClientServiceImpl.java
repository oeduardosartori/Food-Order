package com.sartori.food_order.service.impl;

import com.sartori.food_order.config.MessageResolver;
import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.dto.client.ClientOutputDTO;
import com.sartori.food_order.entity.Client;
import com.sartori.food_order.exception.BusinessException;
import com.sartori.food_order.helper.ErrorMessage;
import com.sartori.food_order.mapper.ClientMapper;
import com.sartori.food_order.repository.ClientRepository;
import com.sartori.food_order.service.ClientService;
import com.sartori.food_order.validator.client.ClientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientValidator clientValidator;
    private final MessageResolver messageResolver;

    @Override
    public ClientOutputDTO createClient(ClientInputDTO inputDTO) {
       clientValidator.validateCreateClient(inputDTO);

       Client client = clientMapper.toEntity(inputDTO);
       Client savedClient = clientRepository.save(client);

       return clientMapper.toOutputDTO(savedClient);
    }

    @Override
    public ClientOutputDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        messageResolver.getMessage(ErrorMessage.CLIENT_NOT_FOUND_BY_ID.code(), id)
                ));

        return clientMapper.toOutputDTO(client);
    }

    @Override
    public Page<ClientOutputDTO> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::toOutputDTO);
    }

    @Override
    public ClientOutputDTO updateClient(Long id, ClientInputDTO inputDTO) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        messageResolver.getMessage(ErrorMessage.CLIENT_NOT_FOUND_BY_ID.code(), id)
                ));

        clientValidator.validateUpdateClient(inputDTO, id);

        existingClient.setName(inputDTO.getName());
        existingClient.setEmail(inputDTO.getEmail());
        existingClient.setCpf(inputDTO.getCpf());
        existingClient.setBirthDate(inputDTO.getBirthDate());

        // Save changes
        Client updateClient = clientRepository.save(existingClient);

        return clientMapper.toOutputDTO(updateClient);
    }

    @Override
    public void delete(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        }
    }
}
