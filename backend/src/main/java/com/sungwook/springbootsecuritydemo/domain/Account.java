package com.sungwook.springbootsecuritydemo.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    private String username;

    private String password;

    private String email;

    @Builder
    public Account(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
