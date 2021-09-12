package com.sungwook.springbootsecuritydemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sungwook.springbootsecuritydemo.domain.dto.LoginRequestDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JwtFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /***
     * 로그인을 시도하면 attempAuthentication함수가 호출된다.
     * authenticationManager에게 인증일 위임한다.
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequestDto loginRequestDto = objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);

        // step1. 토큰생성
        UsernamePasswordAuthenticationToken authentication_token = new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        );

        // step2. 인증위임
        return authenticationManager.authenticate(authentication_token);
    }

    /*** 
     * 인증성공 핸들러
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.debug("인증 성공");
        User user = (User)authResult.getPrincipal();

        // step1. jwt토큰 생성
        JwtUtils jwtUtils = new JwtUtils();
        String jwt_token = jwtUtils.generate(
                user.getUsername(),
                user.getAuthorities()
        );
        log.debug("generate token: " + jwt_token);

        // step2. header에 추가
        response.addHeader("Authentication", "Bearer " + jwt_token);
        super.successfulAuthentication(request, response, chain, authResult);
    }

    /***
     * 인증실패 핸들러
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.debug("인증 실패");
        log.debug(failed.toString());
        super.unsuccessfulAuthentication(request, response, failed);
    }
}