package com.example.Casino.DTO;

import lombok.Data;

@Data
public class TransactionRequest {
    private double amount;
    private String cardNumber;
}