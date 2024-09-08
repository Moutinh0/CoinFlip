package com.example.Casino.controller;

import com.example.Casino.DTO.UserRegistrationRequest;
import com.example.Casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    /*
    @PostMapping
    public String register(@RequestBody UserRegistrationRequest request) {
        boolean success = userService.registerUser(request.getEmail(), request.getPassword(), request.getPhoneNumber());
        if (success) {
            return "User registered successfully";
        } else {
            return "User already exists";
        }
    }
    */
    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest request) {
        try {
            boolean success = userService.registerUser(request.getEmail(), request.getPassword(), request.getPhoneNumber());
            if (success) {
                return ResponseEntity.ok("User registered successfully");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
            }
        } catch (DataIntegrityViolationException ex) {
            // Проверяем, связано ли исключение с нарушением уникальности phone_number
            if (ex.getMessage().contains("phone_number")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Пользователь с таким номером телефона уже существует");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при регистрации пользователя");
        }
    }
}