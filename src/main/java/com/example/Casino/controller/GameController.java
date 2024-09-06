package com.example.Casino.controller;

import com.example.Casino.model.Users;
import com.example.Casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private UserService userService;

    @PostMapping("/coinflip")
    public ResponseEntity<?> coinflip(@RequestParam Double betAmount, @RequestParam String guess, Authentication authentication) {
        Users user = (Users) authentication.getPrincipal();
        if (user.getBalance() < betAmount) {
            return ResponseEntity.badRequest().body("Insufficient funds");
        }

        String result = Math.random() < 0.5 ? "HEADS" : "TAILS";
        boolean win = result.equalsIgnoreCase(guess);

        if (win) {
            userService.updateBalance(user, betAmount);
            return ResponseEntity.ok("You win! New balance: " + user.getBalance());
        } else {
            userService.updateBalance(user, -betAmount);
            return ResponseEntity.ok("You lose! New balance: " + user.getBalance());
        }
    }
}