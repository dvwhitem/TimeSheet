package com.timesheet.web;

import com.timesheet.domain.User;
import com.timesheet.service.impl.UserDetailsServiceImpl;
import com.timesheet.web.dto.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by vitaliy on 03.06.15.
 */
@RestController
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthenticationToken login(@RequestBody User user, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
          user.getLogin(), user.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext()
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());

        System.out.println("USERNAME: " + userDetails.getUsername() +
                " ROLE: " + userDetails.getAuthorities() + " SESSID: " + session.getId());

        return new AuthenticationToken(userDetails.getUsername(), userDetails.getAuthorities(), session.getId());
    }
}
