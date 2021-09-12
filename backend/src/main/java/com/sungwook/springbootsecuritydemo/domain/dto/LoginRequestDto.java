package com.sungwook.springbootsecuritydemo.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * 로그인 요청에 필요한 필드
 */
@Getter
@NoArgsConstructor
@Setter
public class LoginRequestDto {
    private String username;
    private String password;
}
