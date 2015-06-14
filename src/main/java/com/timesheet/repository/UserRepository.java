package com.timesheet.repository;

import com.timesheet.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vitaliy on 04.06.15.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
