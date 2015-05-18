package com.timesheet.service;

import com.timesheet.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by vitaliy on 10.04.15.
 */
public interface TaskService extends GenericService<Task, Long> {

    Page<Task> findAll(Pageable page);

}
