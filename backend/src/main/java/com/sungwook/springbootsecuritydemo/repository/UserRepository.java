package com.sungwook.springbootsecuritydemo.repository;

import com.sungwook.springbootsecuritydemo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
