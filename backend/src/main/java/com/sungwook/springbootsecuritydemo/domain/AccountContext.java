package com.sungwook.springbootsecuritydemo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountContext extends User {
    public AccountContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static AccountContext fromAccount(Account account){
        return new AccountContext(
                account.getUsername(),
                account.getPassword(),
                parseRoles(account.getRole())
        );
    }

    private static List<SimpleGrantedAuthority> parseRoles(AccountRole roles){
        return Arrays.asList(roles).stream()
                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
                .collect(Collectors.toList());
    }
}
