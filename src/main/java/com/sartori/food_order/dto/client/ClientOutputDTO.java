package com.sartori.food_order.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientOutputDTO {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;
}
