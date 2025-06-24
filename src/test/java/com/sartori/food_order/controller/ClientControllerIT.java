package com.sartori.food_order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.dto.client.ClientOutputDTO;
import com.sartori.food_order.entity.Client;
import com.sartori.food_order.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClientControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientRepository clientRepository;

    private ClientInputDTO inputDTO;

    @BeforeEach
    void setUp() {
        inputDTO = new ClientInputDTO();
        inputDTO.setName("Eduardo Sartori");
        inputDTO.setEmail("eduardo.sartori@email.com");
        inputDTO.setCpf("209.788.090-84");
        inputDTO.setBirthDate(LocalDate.of(2000, 4, 10));
    }

    @Test
    @DisplayName("Must create a successful customer and persist in the bank")
    void shouldCreateClientSuccessfully() throws Exception {
        // Arrenge (Preparation)
        String jsonBody = objectMapper.writeValueAsString(inputDTO);

        //Act & Assert (Execution and Verification)
        mockMvc.perform(post("/clients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Eduardo Sartori"));

        // Check if it was saved in the bank
        Client savedClient = clientRepository.findByEmail("eduardo.sartori@email.com").orElseThrow();
        assertEquals("Eduardo Sartori", savedClient.getName());
        assertEquals("20978809084", savedClient.getCpf());
    }
}
