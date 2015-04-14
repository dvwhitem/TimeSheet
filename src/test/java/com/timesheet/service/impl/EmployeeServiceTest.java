package com.timesheet.service.impl;

import com.timesheet.DomainAwareBase;
import com.timesheet.domain.Employee;
import com.timesheet.domain.Manager;
import com.timesheet.domain.Task;
import com.timesheet.domain.Timesheet;
import com.timesheet.service.EmployeeService;
import com.timesheet.service.ManagerService;
import com.timesheet.service.TaskService;
import com.timesheet.service.TimesheetService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vitaliy on 12.04.15.
 */
@ContextConfiguration(locations = {"classpath:persistence-beans.xml"})
public class EmployeeServiceTest extends DomainAwareBase {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TimesheetService timesheetService;

    //@Test
    public void testAdd() {
        int size = employeeService.findAll().size();
        Employee employee = new Employee("Eduardo Sans", "engineer");
        employeeService.save(employee);

        assertTrue(size < employeeService.findAll().size());
    }

    //@Test
    public void testUpdate() {
        Employee employee = new Employee("Luis Gerardo", "test engineer");
        employeeService.save(employee);

        employee.setName("Juan Manuel");
        employeeService.save(employee);
        Employee found = employeeService.findById(employee.getId());
        assertEquals("Juan Manuel", found.getName());
    }

    //@Test
    public void testFind() {
        Employee employee = new Employee("Carlos Iturbe", "manager");
        employeeService.save(employee);

        Employee found = employeeService.findById(employee.getId());
        assertEquals(found, employee);
    }

    //@Test
    public void testList() {
        assertEquals(0, employeeService.findAll().size());

        List<Employee> employees = Arrays.asList(
                new Employee("John", "engineer"),
                new Employee("Garry", "manager"),
                new Employee("Robert", "tester")
        );

        for(Employee employee: employees) {
            employeeService.save(employee);
        }

        List<Employee> found = employeeService.findAll();
        assertEquals(3, found.size());

        for(Employee employee: found) {
            assertTrue(employees.contains(employee));
        }
    }

    //@Test
    public void testRemove() {
        Employee employee = new Employee("Jerry Simons", "team lead");
        employeeService.save(employee);

        // success if added new employee
        assertEquals(employee, employeeService.findById(employee.getId()));
        // try delete
        employeeService.delete(employee);
        assertNull(employeeService.findById(employee.getId()));
    }

    @Test
    public void testRemoveEmployee() {
        Manager manager = new Manager("general manager");
        managerService.save(manager);

        Employee employee = new Employee("Bruce", "security");
        employeeService.save(employee);

        Employee found = employeeService.findById(employee.getId());
        assertEquals("Bruce", found.getName());


        Task task = new Task("some task", manager, employee);
        taskService.save(task);

        Timesheet timesheet = new Timesheet(employee, task, 400);
        timesheetService.save(timesheet);

        assertFalse(employeeService.deleteEmployee(employee));

        timesheetService.delete(timesheet);
        taskService.delete(task);

        assertTrue(employeeService.deleteEmployee(employee));
    }

}
