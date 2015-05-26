package com.timesheet.service;

import com.timesheet.domain.Employee;
import com.timesheet.domain.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Vitaliy, Yan on 10.04.15.
 */
public interface ManagerService extends GenericService<Manager, Long> {

    Page<Manager> findAll(Pageable page);

    boolean deleteManager(Manager manager);
}
