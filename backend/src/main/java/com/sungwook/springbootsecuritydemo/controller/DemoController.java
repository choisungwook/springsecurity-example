package com.sungwook.springbootsecuritydemo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/")
    public String main(){
        return "hello world";
    }

    @GetMapping("/access_any")
    public DemoResponse access_any(){
        return DemoResponse.builder()
                .auth(SecurityContextHolder.getContext().getAuthentication())
                .build();
    }

    @GetMapping("/access_login_required")
    public DemoResponse access_login_required (){
        return DemoResponse.builder()
                .auth(SecurityContextHolder.getContext().getAuthentication())
                .build();
    }
}
