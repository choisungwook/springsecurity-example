package com.sungwook.springbootsecuritydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // "/a"api 요청은 모두 허용하고 나머지는 인증요구
                .authorizeRequests()
                    .antMatchers("/a").permitAll()
                    .anyRequest().authenticated()
                    .and()
                // 로그인 페이지는 모두 허용
                .formLogin()
                    .permitAll()
                    .and()
                //  로그아웃 페이지는 모두 허용
                .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("aaa").password("{noop}password").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("bbb").password("{noop}password").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("ccc").password("{noop}password").roles("ADMIN");
    }
}
