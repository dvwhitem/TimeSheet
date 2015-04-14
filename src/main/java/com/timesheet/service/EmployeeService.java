package com.timesheet.service;

import com.timesheet.domain.Employee;

import java.util.List;

/**
 * Created by vitaliy on 10.04.15.
 */
public interface EmployeeService {

     List<Employee> findAll();

     Employee save(Employee employee);

     Employee findById(Long id);

     void delete(Employee employee);

     boolean deleteEmployee(Employee employee);
}
