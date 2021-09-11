package com.sungwook.springbootsecuritydemo.service;

import com.sungwook.springbootsecuritydemo.domain.Account;
import com.sungwook.springbootsecuritydemo.domain.AccountRole;
import com.sungwook.springbootsecuritydemo.domain.dto.CreateAccountRequestDto;
import com.sungwook.springbootsecuritydemo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Long CreateUser(CreateAccountRequestDto request_body){
        // default: 일방사용자 role적용

        Optional<Account> exist_user = userRepository.findByUsername(request_body.getUsername());
        // 가입된 사용자가 있으면 예외
        if(exist_user.isPresent()) {
            log.debug("회원가입 - 이미 존재하는 회원이어서 가입 실패 ->" + request_body.getUsername());
            throw new IllegalStateException("이미 가입된 사용자입니다");
        }

        Account new_user = Account.builder()
                .username(request_body.getUsername())
                .email(request_body.getEmail())
                .password(passwordEncoder.encode(request_body.getPassword()))
                .role(AccountRole.ROLE_USER)
                .build();

        return userRepository.save(new_user).getId();
    }

}
