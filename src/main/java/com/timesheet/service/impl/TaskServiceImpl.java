package com.timesheet.service.impl;

import com.google.common.collect.Lists;
import com.timesheet.domain.Task;
import com.timesheet.repository.TaskRepository;
import com.timesheet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaliy, Yan on 10.04.15.
 */
@Service("taskService")
@Repository
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @PersistenceContext
    private EntityManager entityManager;




    public Page<Task> findAll(Pageable page) {
        return taskRepository.findAll(page);
    }

    public List<Task> findAll() {
        return Lists.newArrayList(taskRepository.findAll());
    }

    public Task findById(Long id) {
        return taskRepository.findOne(id);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }


    List<Task> tasks = new ArrayList<Task>();


    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public void updateTask(Task task) {
        for (Task tks : tasks) {
            if (tks.getId().longValue() == task.getId().longValue()) {
                int index = tasks.indexOf(tks);
                tasks.set(index, task);
                break;
            }
        }
    }

    @Override
    public void deleteTask(Long id) {
        for(Task tk : tasks){
            if(tk.getId().longValue() == id.longValue()) {
                tasks.remove(tk);
                break;
            }
        }
    }

    @Override
    public void addTask(Task task) {
        Long l = Long.MAX_VALUE;
        for (Task tk : tasks) {
            l = tk.getId();
        }
        task.setId(l + 1);
        tasks.add(task);
    }









    public void delete(Task task) {
        taskRepository.delete(task);
    }


}
