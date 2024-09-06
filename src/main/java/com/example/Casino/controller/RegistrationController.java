package com.example.Casino.controller;

import com.example.Casino.DTO.UserRegistrationRequest;
import com.example.Casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String register(@RequestBody UserRegistrationRequest request) {
        boolean success = userService.registerUser(request.getEmail(), request.getPassword(), request.getPhoneNumber());
        if (success) {
            return "User registered successfully";
        } else {
            return "User already exists";
        }
    }
}