package com.example.Casino.model;


import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "transaction_details")
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String type; // "withdrawal" or "deposit"

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();
}