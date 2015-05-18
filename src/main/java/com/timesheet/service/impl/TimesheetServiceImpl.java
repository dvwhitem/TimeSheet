package com.timesheet.service.impl;

import com.google.common.collect.Lists;
import com.timesheet.domain.Employee;
import com.timesheet.domain.Manager;
import com.timesheet.domain.Task;
import com.timesheet.domain.Timesheet;
import com.timesheet.repository.TimesheetRepository;
import com.timesheet.service.TaskService;
import com.timesheet.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by vitaliy on 10.04.15.
 */
@Service("timesheetService")
@Repository
@Transactional
public class TimesheetServiceImpl implements TimesheetService {

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    TaskService taskService;

    @PersistenceContext
    EntityManager entityManager;

    public List<Timesheet> findAll() {
      return Lists.newArrayList(timesheetRepository.findAll());
    }

    public Page<Timesheet> findAll(Pageable page) {
        return timesheetRepository.findAll(page);
    }

    public Timesheet findById(Long id) {
        return timesheetRepository.findOne(id);
    }

    public Timesheet save(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    public void delete(Timesheet timesheet) {
        timesheetRepository.delete(timesheet);
    }

    public Task busiestTask() {

        List<Task> tasks = taskService.findAll();
        if(tasks.isEmpty()) {
            return null;
        }

        Task busiest = tasks.get(0);
        for(Task task: tasks) {
            if(task.getAssignedEmployees().size() > busiest.getAssignedEmployees().size()) {
                busiest = task;
            }
        }
        return busiest;
    }

    public List<Task> tasksForEmployee(Employee employee) {
        List<Task> allTasks = taskService.findAll();
        List<Task> taskForEmployee = new ArrayList<Task>();

        for(Task task: allTasks) {
            if(task.getAssignedEmployees().contains(employee)) {
                taskForEmployee.add(task);
            }
        }

        return taskForEmployee;
    }

    public List<Task> tasksForManager(Manager manager) {
        TypedQuery query = entityManager.createQuery(
                "select t from Task t where t.manager.id = :id", Task.class
        );
        query.setParameter("id", manager.getId());
        return query.getResultList();
    }
}
