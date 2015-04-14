package com.timesheet.repository;

import com.timesheet.domain.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vitaliy on 10.04.15.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
