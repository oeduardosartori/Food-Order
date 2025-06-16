package com.sartori.food_order.service;

import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.dto.client.ClientOutputDTO;
import com.sartori.food_order.entity.Client;
import com.sartori.food_order.exception.BusinessException;
import com.sartori.food_order.mapper.ClientMapper;
import com.sartori.food_order.repository.ClientRepository;
import com.sartori.food_order.validator.ClientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientValidator clientValidator;

    @Override
    public ClientOutputDTO createClient(ClientInputDTO clientInputDTO) {
       // Validates unique email and CPF
       clientValidator.validateEmailAndCpfUniqueness(
               clientInputDTO.getEmail(),
               clientInputDTO.getCpf()
       );

       // Convert DTO to entity
       Client client = clientMapper.toEntity(clientInputDTO);

       // Save entity
       client = clientRepository.save(client);

       // Return response
       return clientMapper.toOutputDTO(client);
    }

    @Override
    public ClientOutputDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Client not found with ID: " + id));

        return clientMapper.toOutputDTO(client);
    }

    @Override
    public Page<ClientOutputDTO> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::toOutputDTO);
    }

    @Override
    public ClientOutputDTO updateClient(Long id, ClientInputDTO clientInputDTO) {
        // Check if exists
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Client not found with ID: " + id));

        // Validate e-mail/CPF duplicity ignoring current client
        clientValidator.validateEmailAndCpfUniqueness(
                clientInputDTO.getEmail(),
                clientInputDTO.getCpf(),
                id
        );

        // Update fields
        existingClient.setName(clientInputDTO.getName());
        existingClient.setEmail(clientInputDTO.getEmail());
        existingClient.setCpf(clientInputDTO.getCpf());
        existingClient.setBirthDate(clientInputDTO.getBirthDate());

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
