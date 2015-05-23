package com.timesheet.service;

import com.timesheet.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Vitaliy, Yan on 10.04.15.
 */
public interface EmployeeService extends GenericService<Employee, Long> {

     Page<Employee> findAll(Pageable page);

     boolean deleteEmployee(Employee employee);
}
