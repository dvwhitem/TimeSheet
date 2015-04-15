package com.timesheet.web.helpers;

import com.timesheet.domain.Employee;
import com.timesheet.domain.Manager;
import com.timesheet.domain.Task;
import com.timesheet.domain.Timesheet;
import com.timesheet.service.EmployeeService;
import com.timesheet.service.ManagerService;
import com.timesheet.service.TaskService;
import com.timesheet.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Employee carlos = new Employee("Carlos", "Programming");
        Employee smith = new Employee("Smith", "Agent");
        Employee robert = new Employee("Robert", "freelancer");

        // free employees
        Employee harold = new Employee("Harold", "Actor");
        Employee garry = new Employee("Garry", "Manager");
        Employee kanny = new Employee("Kenny", "Singer");
        Employee luacs = new Employee("Lucas", "Producer");

        addEmployees(carlos, smith, robert, harold, garry, kanny, luacs);


    }

    private void addEmployees(Employee... employees) {
        for (Employee employee: employees) {
            employeeService.save(employee);
        }
    }

    private void addManagers(Manager... managers) {
        for (Manager manager: managers) {
            managerService.save(manager);
        }
    }

    private void addTasks(Task... tasks) {
        for(Task task: tasks) {
            taskService.save(task);
        }
    }

    private void addTimesheet(Timesheet... timesheets) {
        for (Timesheet timesheet: timesheets) {
            timesheetService.save(timesheet);
        }
    }
}
