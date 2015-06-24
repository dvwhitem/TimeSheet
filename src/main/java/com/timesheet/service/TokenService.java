package com.timesheet.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by vitaliy on 23.06.15.
 */
public interface TokenService {

    String getToken(UserDetails userDetails);

    Boolean validate(String token, UserDetails userDetails);

    UserDetails getUserFromToken(String token);
}
