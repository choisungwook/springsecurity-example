package com.sungwook.springbootsecuritydemo;

import com.sungwook.springbootsecuritydemo.domain.dto.CreateAccountRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public Long Signup(@RequestBody CreateAccountRequestDto request_body){
        Long save_userid = userService.CreateUser(request_body);
        return save_userid;
    }
}
