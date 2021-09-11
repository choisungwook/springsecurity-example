package com.sungwook.springbootsecuritydemo.config.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/***
 * 인증전 토큰 생성
 */
public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {
    public PreAuthorizationToken(String username, String password) {
        super(username, password);
    }

    public String GetUserName() {
        return (String)super.getPrincipal();
    }

    public String GetUserPassword() {
        return (String)super.getCredentials();
    }
}
