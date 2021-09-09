package com.sungwook.springbootsecuritydemo.domain;

import lombok.Getter;

@Getter
public enum AccountRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String rolename;

    AccountRole(String rolename) {
        this.rolename = rolename;
    }
}
