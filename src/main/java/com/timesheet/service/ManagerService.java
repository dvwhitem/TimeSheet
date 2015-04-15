package com.timesheet.service;

import com.timesheet.domain.Manager;

import java.util.List;

/**
 * Created by vitaliy on 10.04.15.
 */
public interface ManagerService extends GenericService<Manager, Long> {

    boolean deleteManager(Manager manager);
}
