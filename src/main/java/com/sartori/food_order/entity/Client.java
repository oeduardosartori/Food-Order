package com.sartori.food_order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "client",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_client_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_client_cpf", columnNames = "cpf")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 120)
    private String email;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
}
