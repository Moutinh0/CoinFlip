package com.example.Casino.DTO;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String email;
    private String password;
    private String phoneNumber;
}