package com.sungwook.springbootsecuritydemo.config.security;

import com.sungwook.springbootsecuritydemo.domain.Account;
import com.sungwook.springbootsecuritydemo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomUserDetails implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("username" + username);
        Account find_user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자가 없습니다"));

        // dto
        return getAccountContext(find_user);
    }

    private AccountContext getAccountContext(Account account){
        return AccountContext.fromAccountModel(account);
    }
}
