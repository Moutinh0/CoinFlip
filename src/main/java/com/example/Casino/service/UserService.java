package com.example.Casino.service;

import com.example.Casino.model.BetHistory;
import com.example.Casino.model.Users;

import com.example.Casino.repoz.UserRepository;
import com.example.Casino.repoz.BetHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BetHistoryRepository betHistoryRepository;

    public boolean registerUser(String email, String password, String phoneNumber) {
        if (userRepository.findByEmail(email) != null) {
            return false;
        }

        Users user = new Users();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhone_number(phoneNumber);
        userRepository.save(user);
        return true;
    }

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateBalance(Users user, double newBalance) {
        user.setBalance(newBalance);
        userRepository.save(user);
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Loading user by email: " + email);
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("User not found with email: " + email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        org.springframework.security.core.userdetails.User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(email);
        builder.password(user.getPassword());
        builder.authorities(Collections.emptyList());
        return builder.build();
    }



    public void saveBetHistory(Users user, double betAmount, String betResult, String game) {
        BetHistory betHistory = new BetHistory();
        betHistory.setUser(user);
        betHistory.setBetAmount(betAmount);
        betHistory.setBetResult(betResult);
        betHistory.setBetTime(LocalDateTime.now());
        betHistory.setGame(game);
        betHistoryRepository.save(betHistory);
    }

    public List<BetHistory> getBetHistory(Users user) {
        return betHistoryRepository.findByUser(user);
    }
}