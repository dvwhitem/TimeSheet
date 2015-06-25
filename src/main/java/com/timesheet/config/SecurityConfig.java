package com.timesheet.config;


import com.timesheet.service.TokenService;
import com.timesheet.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by vitaliy on 28.05.15.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(false).userDetailsService(userDetailsService);
    }

    @Autowired
    public AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().
                antMatchers("/index.html", "/webjars/**", "/app/**", "/login/**", "/user", "/favicon.ico").
                permitAll().
                anyRequest().
                authenticated().
                and().
                csrf().
                disable().
                addFilterBefore(csrfHeaderFilter(), CsrfFilter.class).sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.formLogin().
                loginPage("/#/login").
                permitAll();

        http.logout().
                permitAll().
                logoutUrl("/#/logout").
                invalidateHttpSession(true);
    }

    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(
                    HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse,
                    FilterChain filterChain) throws ServletException, IOException {

                String authToken = httpServletRequest.getHeader("x-auth-token");


                if(authToken != null) {

                    UserDetails userDetails = tokenService.getUserFromToken(authToken);

                    if(tokenService.validate(authToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authentication));
                    }
                }

                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
