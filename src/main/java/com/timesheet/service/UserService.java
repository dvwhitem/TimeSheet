package com.timesheet.service;

import com.timesheet.domain.User;

/**
 * Created by vitaliy on 04.06.15.
 */
public interface UserService extends GenericService<User, Long> {

    User findByLogin(String login);

}
