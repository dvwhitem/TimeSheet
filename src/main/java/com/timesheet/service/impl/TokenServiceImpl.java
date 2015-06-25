package com.timesheet.service.impl;

import com.timesheet.domain.User;
import com.timesheet.repository.UserRepository;
import com.timesheet.service.TokenService;
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
        String token = createToken(userDetails);
        saveToken(userDetails, token);
        return token;
    }

    public Boolean validate(String token, UserDetails userDetails) {
        return tokens.containsKey(token);
    }

    public UserDetails getUserFromToken(String token) {
        return tokens.get(token);
    }

    private String getTokenFromDataBase(UserDetails userDetails) {

        User user = userRepository.findByLogin(userDetails.getUsername());
        if(user != null) {
            if(user.getToken() != null) {
                return user.getToken();
            }
        }
        return null;
    }

    private void saveToken(UserDetails userDetails, String newToken) {
        String token = getTokenFromDataBase(userDetails);
        User user = userRepository.findByLogin(userDetails.getUsername());
        if(token == null) {
            user.setToken(newToken);
        }
        user.setTokenUsed(new Date());
        userRepository.save(user);
    }

    private String createToken(UserDetails userDetails) {

        try {
            SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

            String randomNum = new Integer(prng.nextInt()).toString();

            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] result = sha.digest(randomNum.getBytes());

            String token;

            token = getTokenFromDataBase(userDetails);
            if (token == null) {
                token = hexEncode(result);
            }

            tokens.put(token, userDetails);

            return token;

        } catch (Exception e) {

            throw new RuntimeException(e);
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
