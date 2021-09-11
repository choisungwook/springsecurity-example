package com.sungwook.springbootsecuritydemo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CustomProvider customProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // "/a"api 요청은 모두 허용하고 나머지는 인증요구
                .authorizeRequests()
                    .antMatchers("/a").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/signup").permitAll()
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
