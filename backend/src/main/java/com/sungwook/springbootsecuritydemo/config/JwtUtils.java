package com.sungwook.springbootsecuritydemo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

public class JwtUtils {

    private String secret_key = "demo";
    private Algorithm AL = Algorithm.HMAC512(secret_key);
    private long lifetime = 30;

    /***
     * jwt 토큰 생성
     * @param username
     * @return
     */
    public String generate(String username, Collection<GrantedAuthority> roles){
        return JWT.create()
                .withSubject(username)
                .withClaim("exp", Instant.now().getEpochSecond() + lifetime)
                .withClaim("roles", roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(AL);
    }

}
