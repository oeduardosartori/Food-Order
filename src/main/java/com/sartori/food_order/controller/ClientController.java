package com.sartori.food_order.controller;

import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.dto.client.ClientOutputDTO;
import com.sartori.food_order.dto.response.PageResponse;
import com.sartori.food_order.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientOutputDTO> createClient(@RequestBody @Valid ClientInputDTO clientInputDTO) {
        ClientOutputDTO  createdClient = clientService.createClient(clientInputDTO);
        return ResponseEntity.status(201).body(createdClient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientOutputDTO> getClientById(@PathVariable Long id) {
        ClientOutputDTO client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<PageResponse<ClientOutputDTO>> getAllClients(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<ClientOutputDTO> clients = clientService.getAllClients(pageable);
        return ResponseEntity.ok(new PageResponse<>(clients));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientOutputDTO> updateClient(
            @PathVariable Long id,
            @RequestBody @Valid ClientInputDTO clientInputDTO) {
        ClientOutputDTO updated = clientService.updateClient(id, clientInputDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientOutputDTO> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
