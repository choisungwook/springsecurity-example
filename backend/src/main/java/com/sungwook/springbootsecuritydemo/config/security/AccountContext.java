package com.sungwook.springbootsecuritydemo.config.security;

import com.sungwook.springbootsecuritydemo.domain.Account;
import com.sungwook.springbootsecuritydemo.domain.AccountRole;
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

    public static AccountContext fromAccountModel(Account account) {
        return new AccountContext(
                account.getUsername(),
                account.getPassword(),
                parseAuthorities(account.getRole())
        );
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(AccountRole role){
        return Arrays.asList(role).stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole_name()))
                .collect(Collectors.toList());
    }
}
