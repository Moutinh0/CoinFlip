package com.example.Casino.service;
import com.example.Casino.model.Users;
import com.example.Casino.repoz.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public boolean registerUser(String email, String password, String phoneNumber) {
        if (userRepository.findByEmail(email) != null) {
            // Пользователь с таким email уже существует
            return false;
        }

        if (userRepository.existsByPhoneNumber(phoneNumber)) {

            throw new IllegalArgumentException("Такой номер телефона уже существует");

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
}