package com.timesheet.service;

import com.timesheet.domain.Employee;
import com.timesheet.domain.Manager;
import com.timesheet.domain.Task;
import com.timesheet.domain.Timesheet;

import java.util.List;

/**
 * Created by vitaliy on 09.04.15.
 */
public interface TimesheetService {

    List<Timesheet> findAll();

    Timesheet findById(Long id);

    Timesheet save(Timesheet timesheet);

    void delete(Timesheet timesheet);

    Task busiestTask();

    List<Task> tasksForEmployee(Employee e);

    List<Task> tasksForManager(Manager m);
}
