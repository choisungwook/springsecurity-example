package com.sungwook.springbootsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/signup").permitAll()
                    .antMatchers("/access_any").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .headers()
                    .frameOptions().disable()
                    .and()
                // 로그인 페이지는 모두 허용
                .formLogin()
                    .permitAll()
                    .and()
                //  로그아웃 페이지는 모두 허용
                .logout()
                    .permitAll()
                    .and()
                .csrf().disable();

    }
}
