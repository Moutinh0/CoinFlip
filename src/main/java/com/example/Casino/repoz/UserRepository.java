package com.example.Casino.repoz;

import com.example.Casino.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    @Query("SELECT COUNT(u) > 0 FROM Users u WHERE u.phone_number = :phoneNumber")
    boolean existsByPhoneNumber(@Param("phoneNumber") String phone_number);
}