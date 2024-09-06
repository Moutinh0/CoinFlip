package com.example.Casino.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Setter
//@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phone_number;

    private Double balance = 0.0;  // Начальный баланс пользователя
}