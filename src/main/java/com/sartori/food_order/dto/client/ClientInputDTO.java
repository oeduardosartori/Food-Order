package com.sartori.food_order.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO used to create a new customer")
public class ClientInputDTO {

    @NotBlank(message = "{client.name.notblank}")
    @Schema(description = "Customer's full name", example = "Eduardo Sartori", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "{client.email.notblank}")
    @Email(message = "{client.email.invalid}")
    @Schema(description = "Valid customer email", example = "eduardo@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "{client.cpf.notblank}")
    @CPF(message = "{client.cpf.invalid}")
    @Schema(description = "Valid customer CPF", example = "12345678901", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cpf;

    @NotNull(message = "{client.birthdate.notnull}")
    @Past(message = "{client.birthdate.past}")
    @Schema(description = "Customer's date of birth", example = "1999-01-01", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate birthDate;
}
