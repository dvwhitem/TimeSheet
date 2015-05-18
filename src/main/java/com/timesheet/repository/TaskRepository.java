package com.timesheet.repository;

import com.timesheet.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Yan on 18.04.15.
 */
public interface TaskRepository extends JpaRepository<Task, Long> , CrudRepository<Task, Long> {
}
