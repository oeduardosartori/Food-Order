package com.sartori.food_order.dto.client;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientOutputDTO {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;
}
