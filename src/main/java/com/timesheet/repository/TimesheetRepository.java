package com.timesheet.repository;

import com.timesheet.domain.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vitaliy on 10.04.15.
 */
public interface TimesheetRepository extends JpaRepository<Timesheet, Long>, CrudRepository<Timesheet, Long> {
}
