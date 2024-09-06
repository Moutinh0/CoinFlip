package com.example.Casino.service;

import com.example.Casino.model.TransactionDetail;
import com.example.Casino.model.Users;
import com.example.Casino.repoz.TransactionRepository;
import com.example.Casino.repoz.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    // Создание новой транзакции
    @Transactional
    public TransactionDetail createTransaction(Long userId, Double amount, String type, String cardNumber) {
        // Найти пользователя по ID
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Создание транзакции
        TransactionDetail transaction = new TransactionDetail();
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setDate(LocalDateTime.now());
        transaction.setCardNumber(cardNumber);

        // Сохранение транзакции в БД
        return transactionRepository.save(transaction);
    }

    // Получение всех транзакций пользователя
    public List<TransactionDetail> getUserTransactions(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return transactionRepository.findByUserId(userId); // Использование существующего метода
    }
}