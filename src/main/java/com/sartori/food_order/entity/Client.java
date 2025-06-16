package com.sartori.food_order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(unique = true, nullable = false,length = 120)
    private String email;

    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(name = "birth_Date", nullable = false)
    private LocalDate birthDate;
}
