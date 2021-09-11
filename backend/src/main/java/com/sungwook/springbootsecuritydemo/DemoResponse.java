package com.sungwook.springbootsecuritydemo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;

/***
 * 로그인 정보(auth token)를 응답으로 넘기기위한 response 객체
 */
@Getter
@Setter
public class DemoResponse {

    private Authentication auth;

    @Builder
    public DemoResponse(Authentication auth) {
        this.auth = auth;
    }
}
