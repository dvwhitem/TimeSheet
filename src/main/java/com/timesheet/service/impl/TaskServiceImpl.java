package com.timesheet.service.impl;

import com.google.common.collect.Lists;
import com.timesheet.domain.Task;
import com.timesheet.repository.TaskRepository;
import com.timesheet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vitaliy on 10.04.15.
 */
@Service("taskService")
@Repository
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    public List<Task> findAll() {
        return Lists.newArrayList(taskRepository.findAll());
    }

    public Task findById(Long id) {
        return taskRepository.findOne(id);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }
}
