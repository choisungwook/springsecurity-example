package com.sungwook.springbootsecuritydemo.repository;

import com.sungwook.springbootsecuritydemo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {
}
