package com.timesheet.repository;

import com.timesheet.domain.Timesheet;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vitaliy on 10.04.15.
 */
public interface TimesheetRepository extends CrudRepository<Timesheet, Long> {
}
