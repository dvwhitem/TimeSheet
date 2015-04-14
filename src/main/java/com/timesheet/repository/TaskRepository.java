package com.timesheet.repository;

import com.timesheet.domain.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vitaliy on 10.04.15.
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
}
