package com.timesheet.web;

import com.timesheet.domain.User;
import com.timesheet.service.TokenService;
import com.timesheet.service.impl.UserDetailsServiceImpl;
import com.timesheet.transfer.UserTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
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
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/user")
    public UserTransfer user(HttpServletRequest request) {

        HttpSession session = request.getSession(true);

        SecurityContext securityContext =
                        (SecurityContext) session.getAttribute(
                                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY
                        );

        if(securityContext != null) {
            Authentication authentication = securityContext.getAuthentication();
            Object principal = authentication.getPrincipal();
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }

            UserDetails userDetails = (UserDetails) principal;
            return new UserTransfer(userDetails.getUsername(), userDetails.getAuthorities(), tokenService.getToken(userDetails));
        }
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserTransfer login(@RequestBody User user, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
          user.getLogin(), user.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext()
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());

        return new UserTransfer(userDetails.getUsername(), userDetails.getAuthorities(), tokenService.getToken(userDetails));
    }


}
