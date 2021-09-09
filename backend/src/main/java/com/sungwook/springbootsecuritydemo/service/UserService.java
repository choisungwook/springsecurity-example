package com.sungwook.springbootsecuritydemo.service;

import com.sungwook.springbootsecuritydemo.domain.Account;
import com.sungwook.springbootsecuritydemo.domain.dto.CreateAccountRequestDto;
import com.sungwook.springbootsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Long CreateUser(CreateAccountRequestDto request_body){
        Account new_user = Account.builder()
                .username(request_body.getUsername())
                .email(request_body.getEmail())
                .password(passwordEncoder.encode(request_body.getPassword()))
                .build();

        return userRepository.save(new_user).getId();
    }
}
