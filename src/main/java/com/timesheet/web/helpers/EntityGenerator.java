package com.timesheet.web.helpers;

import com.timesheet.domain.Employee;
import com.timesheet.domain.Manager;
import com.timesheet.domain.Task;
import com.timesheet.domain.Timesheet;
import com.timesheet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by go1095 on 4/15/15.
 */
@Service
public final class EntityGenerator {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TimesheetService timesheetService;

    public void generateDomain() {

        // Employee
        Employee carlos = new Employee("Carlos", "Programming");
        Employee smith = new Employee("Smith", "Agent");
        Employee robert = new Employee("Robert", "freelancer");
        // free employees
        Employee harold = new Employee("Harold", "Actor");
        Employee garry = new Employee("Garry", "Manager");
        Employee kanny = new Employee("Kenny", "Singer");
        Employee luacs = new Employee("Lucas", "Producer");
        Employee albert = new Employee("Albert", "Manager");
        Employee sekas = new Employee("Se Kosugi", "Sales manager");
        // add employee
        addAll(employeeService, carlos, smith, robert, harold, garry, kanny, luacs, albert, sekas);

        // Manager
        Manager bill = new Manager("Bill");
        Manager luiz = new Manager("Luiz");
        // free manager
        Manager david = new Manager("David");
        Manager poul = new Manager("Poul");
        // add manager
        addAll(managerService, bill, luiz, david, poul);

        // Task
        Task planTask = new Task("Create plan", bill, carlos, robert);
        Task developTask = new Task("Develop project", bill, smith);
        Task testTask = new Task("Testing project", luiz, robert);
        Task managerTask = new Task("Manager project", poul, albert);
        addAll(taskService, planTask, developTask, testTask, managerTask);

        Timesheet robertOnCreatePlan = new Timesheet(robert, planTask, 60);
        Timesheet smithOnDevelopProject = new Timesheet(smith, developTask, 36);
        Timesheet sekasOnManagerProject = new Timesheet(sekas, managerTask, 90);

        addAll(timesheetService, robertOnCreatePlan, smithOnDevelopProject, sekasOnManagerProject);
    }

    public void deleteDomain() {

        List<Timesheet> timesheets = timesheetService.findAll();
        for(Timesheet timesheet: timesheets) {
            timesheetService.delete(timesheet);
        }

        List<Task> tasks = taskService.findAll();
        for(Task task: tasks) {
            taskService.delete(task);
        }

        List<Manager> managers = managerService.findAll();
        for(Manager manager: managers) {
            managerService.delete(manager);
        }

        List<Employee> employees = employeeService.findAll();
        for(Employee employee: employees) {
            employeeService.delete(employee);
        }

    }

    private <T> void addAll(GenericService<T, Long> service, T... entities) {
        for(T o: entities) {
            service.save(o);
        }
    }
}
