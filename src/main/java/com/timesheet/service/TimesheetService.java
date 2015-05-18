package com.timesheet.service;

import com.timesheet.domain.Employee;
import com.timesheet.domain.Manager;
import com.timesheet.domain.Task;
import com.timesheet.domain.Timesheet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by vitaliy on 09.04.15.
 */
public interface TimesheetService extends GenericService<Timesheet, Long> {

    Task busiestTask();

    Page<Timesheet> findAll(Pageable page);

    List<Task> tasksForEmployee(Employee e);

    List<Task> tasksForManager(Manager m);
}
