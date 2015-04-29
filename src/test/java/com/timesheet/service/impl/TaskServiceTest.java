package com.timesheet.service.impl;

import com.timesheet.DomainAwareBase;
import com.timesheet.config.PersistenceConfig;
import com.timesheet.config.PropertiesConfig;
import com.timesheet.config.TransactionConfig;
import com.timesheet.domain.Employee;
import com.timesheet.domain.Manager;
import com.timesheet.domain.Task;
import com.timesheet.service.EmployeeService;
import com.timesheet.service.ManagerService;
import com.timesheet.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vitaliy on 13.04.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfig.class, TransactionConfig.class, PersistenceConfig.class})
public class TaskServiceTest extends DomainAwareBase {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testAdd() {
        int size = taskService.findAll().size();

        Task task = newSpringTask();
        taskService.save(task);

        assertTrue(size < taskService.findAll().size());
    }

    //@Test
    public void testUpdate() {
        Task task = newSpringTask();
        taskService.save(task);

        task.setDescription("Learn Spring 4.1");
        taskService.save(task);

        Task found = taskService.findById(task.getId());
        assertEquals("Learn Spring 4.1", found.getDescription());
    }

    //@Test
    public void testFind() {
        Task task = newSpringTask();
        taskService.save(task);

        assertEquals(task, taskService.findById(task.getId()));
    }

    //@Test
    public void testList() {
        assertEquals(0, taskService.findAll().size());
        Task templateTask = newSpringTask();

        List<Task> tasks = Arrays.asList(
                newTaskFromTemplate(templateTask, "1"),
                newTaskFromTemplate(templateTask, "2"),
                newTaskFromTemplate(templateTask, "3")
        );

        for(Task task: tasks) {
            taskService.save(task);
        }

        List<Task> found = taskService.findAll();
        assertEquals(3, found.size());
        for(Task task: found) {
            assertTrue(tasks.contains(task));
        }
    }

    //@Test
    public void testRemove() {
        Task task = newSpringTask();
        taskService.save(task);

        assertEquals(task, taskService.findById(task.getId()));

        taskService.delete(task);
        assertNull(taskService.findById(task.getId()));
    }

    private Task newSpringTask() {
        Manager javi = new Manager("Javi Pereira");
        managerService.save(javi);

        Employee carlos = new Employee("Carlos", "developer");
        Employee john = new Employee("John", "engineer");
        employeeService.save(carlos);
        employeeService.save(john);

        return new Task("Learn Spring Pro", javi, carlos, john);
    }

    private Task newTaskFromTemplate(Task templateTask, String rand) {
        String desc = templateTask.getDescription() + rand;

        Manager manager = new Manager(templateTask.getManager().getName());
        managerService.save(manager);

        List<Employee> templateEmployees = templateTask.getAssignedEmployees();
        Employee[]  employees = new Employee[templateEmployees.size()];

        int i = 0;
        for(Employee templateEmployee: templateEmployees) {
            Employee employee =
                    new Employee(
                            templateEmployee.getName() + rand,
                            templateEmployee.getDepartment() + rand
                    );
            employees[i++] = employee;
            employeeService.save(employee);
        }
        return new Task(desc, manager, employees);
    }
}
