package com.timesheet.service.impl;

import com.timesheet.domain.User;
import com.timesheet.repository.UserRepository;
import com.timesheet.service.TokenService;
import com.timesheet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vitaliy on 23.06.15.
 */
@Service("TokenService")
@Repository
public class TokenServiceImpl implements TokenService {

    @Autowired
    private UserRepository userRepository;

    private Map<String, UserDetails> tokens = new HashMap<String, UserDetails>();

    public String getToken(UserDetails userDetails) {
        try {
            SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

            String randomNum = new Integer(prng.nextInt()).toString();

            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] result = sha.digest(randomNum.getBytes());

            String token = (String) getKeyFromValue(tokens, userDetails);
            if(!token.isEmpty() && token != null) {
                return token;
            }

            token = hexEncode(result);

            if(!isTokenFromDataBase(userDetails)) {
                saveToken(token);
            } else {
                tokens.put(token, userDetails);
            }

            return token;

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public Boolean validate(String token, UserDetails userDetails) {

        if(!isTokenFromDataBase(userDetails)) {
            saveToken(token);
        }

        return tokens.containsKey(token);
    }

    public UserDetails getUserFromToken(String token) {
        if(tokens.get(token) == null) {
            User user = userRepository.findByToken(token);
        }
        return tokens.get(token);
    }

    private Object getKeyFromValue(Map tokens, Object value) {
        for (Object token : tokens.keySet()) {
            if (tokens.get(token).equals(value)) {
                return token;
            }
        }
        return null;
    }

    private boolean isTokenFromDataBase(UserDetails userDetails) {

        if(userDetails != null) {
            User user = userRepository.findByLogin(userDetails.getUsername());
            if (user.getToken() != null) {
                tokens.put(user.getToken(), userDetails);
                return true;
            }
        }
        return false;
    }

    private void saveToken(String token) {
        User user = userRepository.findByToken(token);
        if(user != null) {
            user.setToken(token);
            user.setTokenUsed(new Date());
            userRepository.save(user);
        }
    }

    private String hexEncode(byte[] aInput) {
        StringBuilder result = new StringBuilder();
        char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        for (int idx = 0; idx < aInput.length; ++idx) {
            byte b = aInput[idx];
            result.append(digits[(b & 0xf0) >> 4]);
            result.append(digits[b & 0x0f]);
        }
        return result.toString();
    }
}
