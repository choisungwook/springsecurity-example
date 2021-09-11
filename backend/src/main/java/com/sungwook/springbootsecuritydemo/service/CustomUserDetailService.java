package com.sungwook.springbootsecuritydemo.service;

import com.sungwook.springbootsecuritydemo.domain.Account;
import com.sungwook.springbootsecuritydemo.domain.AccountContext;
import com.sungwook.springbootsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /***
     * 데이터베이스를 조회하여 사용자를 찾는다.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account find_user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자가 없습니다"));

        return AccountContext.fromAccount(find_user);
    }
}
