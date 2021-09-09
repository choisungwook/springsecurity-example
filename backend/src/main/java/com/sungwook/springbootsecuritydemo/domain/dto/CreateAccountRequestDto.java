package com.sungwook.springbootsecuritydemo.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequestDto {
    private String username;
    private String email;
    private String password;
}
