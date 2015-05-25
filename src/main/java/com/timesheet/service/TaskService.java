package com.timesheet.service;

import com.timesheet.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Vitaliy, Yan on 10.04.15.
 */
public interface TaskService extends GenericService<Task, Long> {

    Page<Task> findAll(Pageable page);

    List<Task> getAll();
    void updateTask(Task task);
    void deleteTask(Long id);
    void addTask(Task task);


}
