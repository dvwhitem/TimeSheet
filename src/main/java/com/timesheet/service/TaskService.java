package com.timesheet.service;

import com.timesheet.domain.Task;

import java.util.List;

/**
 * Created by vitaliy on 10.04.15.
 */
public interface TaskService {

    List<Task> findAll();

    Task findById(Long id);

    Task save(Task task);

    void delete(Task task);
}
