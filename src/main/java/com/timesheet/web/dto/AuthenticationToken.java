package com.timesheet.web.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

/**
 * Created by vitaliy on 12.06.15.
 */
public class AuthenticationToken {

    private String login;
    private Collection<? extends GrantedAuthority> roles;
    private String token;

    public AuthenticationToken() {
    }

    public AuthenticationToken(String login, Collection<? extends GrantedAuthority> roles, String token) {
        this.login = login;
        this.roles = roles;
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
