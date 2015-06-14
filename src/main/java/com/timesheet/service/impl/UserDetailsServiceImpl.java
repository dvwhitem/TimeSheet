package com.timesheet.service.impl;

import com.timesheet.domain.Role;
import com.timesheet.domain.User;
import com.timesheet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vitaliy on 04.06.15.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByLogin(login);

        UserDetails userDetails;

        if(user == null) {
            throw new UsernameNotFoundException("username: " + login + " not found");
        } else {
            Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();

            for (Role role: user.getRoles()) {
                roles.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            userDetails =
                    new org.springframework.security.core.userdetails.User(
                            user.getLogin(),
                            user.getPassword(),
                            roles
                    );
        }
        return userDetails;
    }
}
