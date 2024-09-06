package com.example.Casino.repoz;


import com.example.Casino.model.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface TransactionRepository extends JpaRepository<TransactionDetail, Long> {
    List<TransactionDetail> findByUserId(Long userId);

}