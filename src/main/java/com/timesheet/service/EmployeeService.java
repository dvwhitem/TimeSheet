package com.timesheet.service;

import com.timesheet.domain.Employee;

import java.util.List;

/**
 * Created by vitaliy on 10.04.15.
 */
public interface EmployeeService extends GenericService<Employee, Long> {

     boolean deleteEmployee(Employee employee);
}
