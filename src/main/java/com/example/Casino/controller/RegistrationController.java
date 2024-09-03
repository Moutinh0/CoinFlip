package com.example.Casino.controller;

import com.example.Casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping

    //String
    public String register(@RequestBody UserRegistrationRequest request) {

        System.out.println(request);
        boolean success = userService.registerUser(request.getEmail(), request.getPassword(), request.getPhoneNumber());
        if (success) {
            //return "User registered successfully";
            return "User registered successfully";
        } else {
            return "User already exists";
        }
    }
}

class UserRegistrationRequest {
    private String email;
    private String password;
    private String phone_number;  // Изменено на phone_number

    // Getters and setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhoneNumber() { return phone_number; }
    public void setPhoneNumber(String phoneNumber) { this.phone_number = phoneNumber; }
}