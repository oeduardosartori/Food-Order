package com.sartori.food_order.service.impl;

import com.sartori.food_order.config.MessageResolver;
import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.dto.client.ClientOutputDTO;
import com.sartori.food_order.entity.Client;
import com.sartori.food_order.exception.BusinessException;
import com.sartori.food_order.helper.ErrorMessage;
import com.sartori.food_order.mapper.ClientMapper;
import com.sartori.food_order.repository.ClientRepository;
import com.sartori.food_order.validator.client.ClientValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private ClientValidator clientValidator;

    @Mock
    private MessageResolver messageResolver;

    private ClientInputDTO inputDTO;
    private Client clientEntity;
    private Client savedClient;
    private ClientOutputDTO outputDTO;

    @BeforeEach
    void setUp() {
        inputDTO = new ClientInputDTO();
        inputDTO.setName("Eduardo Sartori");
        inputDTO.setEmail("eduardo.sartori@email.com");
        inputDTO.setCpf("209.788.090-84");
        inputDTO.setBirthDate(LocalDate.of(2000, 4, 10));

        clientEntity = new Client();
        clientEntity.setName("Eduardo Sartori");
        clientEntity.setEmail("eduardo.sartori@email.com");
        clientEntity.setCpf("209.788.090-84");
        clientEntity.setBirthDate(LocalDate.of(2000, 4, 10));

        savedClient = new Client();
        savedClient.setId(1L);
        savedClient.setName("Eduardo Sartori");
        savedClient.setEmail("eduardo.sartori@email.com");
        savedClient.setCpf("209.788.090-84");
        savedClient.setBirthDate(LocalDate.of(2000, 4, 10));

        outputDTO = new ClientOutputDTO();
        outputDTO.setId(1L);
        outputDTO.setName("Eduardo Sartori");
        outputDTO.setEmail("eduardo.sartori@email.com");
        outputDTO.setCpf("209.788.090-84");
        outputDTO.setBirthDate(LocalDate.of(2000, 4, 10));
    }

    @Test
    @DisplayName("Must create a client successfully")
    void shouldCreateClientSuccessfully() {
        // Arrange (Preparation)
        when(clientMapper.toEntity(inputDTO)).thenReturn(clientEntity);
        when(clientRepository.save(clientEntity)).thenReturn(savedClient);
        when(clientMapper.toOutputDTO(savedClient)).thenReturn(outputDTO);

        // Act (Execution)
        ClientOutputDTO result = clientService.createClient(inputDTO);

        // Assert (Verification)
        assertNotNull(result);
        assertEquals("Eduardo Sartori", result.getName());
        assertEquals(1L, result.getId());

        // Additional checks
        verify(clientValidator).validateCreateClient(inputDTO);
        verify(clientRepository).save(clientEntity);
    }

    @Test
    @DisplayName("Should throw exception when trying to create client with existing CPF")
    void shouldThrowExceptionWhenCpfIsDuplicated() {
        // Arrange (Preparation)
        doThrow(new BusinessException(messageResolver.getMessage(ErrorMessage.CLIENT_CPF_DUPLICATE.code())))
                .when(clientValidator).validateCreateClient(inputDTO);

        //Act & Assert (Execution and Verification)
        BusinessException exception = assertThrows(BusinessException.class, () ->
                clientService.createClient(inputDTO)
        );

        assertEquals(messageResolver.getMessage(ErrorMessage.CLIENT_CPF_DUPLICATE.code()), exception.getMessage());
        verify(clientValidator).validateCreateClient(inputDTO);
        verify(clientRepository, never()).save(any()); // Nothing should be saved
    }

    @Test
    @DisplayName("Should return a customer when searching by existing ID")
    void shouldReturnClientByIdSuccessfully() {
        // Arrange (Preparation)
        Long id = 1L;
        when(clientRepository.findById(id)).thenReturn(Optional.of(clientEntity));
        when(clientMapper.toOutputDTO(clientEntity)).thenReturn(outputDTO);

        // Act (Execution)
        ClientOutputDTO result = clientService.getClientById(id);

        // Assert (Verification)
        verify(clientRepository).findById(id);
        verify(clientMapper).toOutputDTO(clientEntity);
    }

    @Test
    @DisplayName("Should throw exception when searching for client with non-existent ID")
    void shouldThrowExceptionClientNotFound() {
        // Arrange (Preparation)
        Long id = 99L;
        when(clientRepository.findById(id)).thenReturn(Optional.empty());
        when(messageResolver.getMessage(ErrorMessage.CLIENT_NOT_FOUND_BY_ID.code(), id))
                .thenReturn("Customer with ID 99 not found");

        //Act & Assert (Execution and Verification)
        BusinessException exception = assertThrows(BusinessException.class, () ->
                clientService.getClientById(id)
        );

        assertEquals("Customer with ID 99 not found", exception.getMessage());
        verify(clientRepository).findById(id);
        verify(clientMapper, never()).toOutputDTO(any());
    }
}
