package com.sungwook.springbootsecuritydemo.domain;

import lombok.Getter;

@Getter
public enum AccountRole {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String role_name;

    AccountRole(String role_name) {
        this.role_name = role_name;
    }
}
