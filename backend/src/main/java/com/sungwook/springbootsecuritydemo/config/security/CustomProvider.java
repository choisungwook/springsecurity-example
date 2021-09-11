package com.sungwook.springbootsecuritydemo.config.security;

import com.sungwook.springbootsecuritydemo.config.security.token.PostAuthorizationToken;
import com.sungwook.springbootsecuritydemo.config.security.token.PreAuthorizationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Slf4j
public class CustomProvider implements AuthenticationProvider {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetails customUserDetails;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("customprovider is called");
        PreAuthorizationToken token = (PreAuthorizationToken)authentication;
        String username = token.getName();
        String password = token.GetUserPassword();

        AccountContext accountContext = (AccountContext) customUserDetails.loadUserByUsername(username);

        if(isCorrectPassword(password, accountContext.getPassword())){
            return new PostAuthorizationToken(accountContext, accountContext.getPassword(), accountContext.getAuthorities());
        }

        throw new NoSuchElementException("사용자 인증에 실패했습니다");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private boolean isCorrectPassword(String password, String input_password){
        return passwordEncoder.matches(password, input_password);
    }
}
