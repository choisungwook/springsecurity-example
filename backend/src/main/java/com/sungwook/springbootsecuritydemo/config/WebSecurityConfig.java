package com.sungwook.springbootsecuritydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // "/"api 요청은 모두 허용하고 나머지는 deny
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .and()
                // 로그인 페이지는 모두 허용
                .formLogin()
                    .permitAll()
                    .and()
                //  로그아웃 페이지는 모두 허용
                .logout()
                    .permitAll();
    }
}
