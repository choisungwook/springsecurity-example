package com.sungwook.springbootsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class InMemoryDemoUser {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    UserDetailsService users() {
        UserDetails admin_user = User.builder()
                .username("admin_user")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .build();

        UserDetails normal_user = User.builder()
                .username("normal_user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin_user, normal_user);
    }
}
