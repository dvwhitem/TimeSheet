package com.timesheet.service.impl;

import com.google.common.collect.Lists;
import com.timesheet.domain.User;
import com.timesheet.repository.UserRepository;
import com.timesheet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vitaliy on 04.06.15.
 */
@Service("userService")
@Repository
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    public List<User> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
