package com.sartori.food_order.service;

import com.sartori.food_order.dto.client.ClientInputDTO;
import com.sartori.food_order.dto.client.ClientOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    ClientOutputDTO createClient(ClientInputDTO clientInputDTO);
    ClientOutputDTO getClientById(Long id);
    Page<ClientOutputDTO> getAllClients(Pageable pageable);
    ClientOutputDTO updateClient(Long id, ClientInputDTO clientInputDTO);
    void delete(Long id);
}
