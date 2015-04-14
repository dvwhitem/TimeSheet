package com.timesheet.service;

import com.timesheet.domain.Manager;

import java.util.List;

/**
 * Created by vitaliy on 10.04.15.
 */
public interface ManagerService {

    List<Manager> findAll();

    Manager findById(Long id);

    Manager save(Manager manager);

    void delete(Manager manager);

    boolean deleteManager(Manager manager);
}
