package com.example.Casino.controller;
import com.example.Casino.DTO.JwtResponse;
import com.example.Casino.DTO.LoginRequest;
import com.example.Casino.DTO.UserRegistrationRequest;
import com.example.Casino.JWT.JwtTokenUtil;
import com.example.Casino.model.Users;
import com.example.Casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest request) {
        try {
            userService.registerUser(request.getEmail(), request.getPassword(), request.getPhoneNumber());
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Users user = userService.findByEmail(request.getEmail());
        if (user != null && new BCryptPasswordEncoder().matches(request.getPassword(), user.getPassword())) {
            // Используем UserDetails для генерации JWT токена
            UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
            String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}