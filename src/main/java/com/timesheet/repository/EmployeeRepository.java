package com.timesheet.repository;

import com.timesheet.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vitaliy on 10.04.15.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
