package com.timesheet.repository;

import com.timesheet.domain.Manager;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vitaliy, Yan on 10.04.15.
 */
public interface ManagerRepository extends CrudRepository<Manager, Long> {
}
