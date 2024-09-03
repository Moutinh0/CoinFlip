package com.example.Casino.controller;
import com.example.Casino.model.Users;
import com.example.Casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public Users getUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            return userService.findByEmail(email);
        }
        return null;
    }
}