package com.timesheet.repository;

import com.timesheet.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vitaliy, Yan on 10.04.15.
 */
public interface ManagerRepository  extends JpaRepository<Manager, Long>, CrudRepository<Manager, Long> {
}
