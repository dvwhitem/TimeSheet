package com.timesheet.service.impl;

import com.timesheet.DomainAwareBase;
import com.timesheet.config.PersistenceConfig;
import com.timesheet.config.PropertiesConfig;
import com.timesheet.config.TransactionConfig;
import com.timesheet.domain.Employee;
import com.timesheet.domain.Manager;
import com.timesheet.domain.Task;
import com.timesheet.domain.Timesheet;
import com.timesheet.service.EmployeeService;
import com.timesheet.service.ManagerService;
import com.timesheet.service.TaskService;
import com.timesheet.service.TimesheetService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by Vitaliy, Yan on 14.04.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfig.class, TransactionConfig.class, PersistenceConfig.class})
public class TimesheetServiceTest extends DomainAwareBase {

    @Autowired
    private TimesheetService timesheetService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ManagerService managerService;

    private Task task;

    private Employee employee;

    @Override
    public void deleteAllDomainEntities() throws Exception {
        super.deleteAllDomainEntities();
        setUp();
    }

    public void setUp() {
        employee = new Employee("Scott", "scout agent");
        employeeService.save(employee);

        Manager manager = new Manager("Roger");
        managerService.save(manager);

        task = new Task("Learning Spring 4.1", manager, employee);
        taskService.save(task);
    }

    //@Test
    public void testAdd() {
        int size = timesheetService.findAll().size();

        Timesheet timesheet = newTimesheet();
        timesheetService.save(timesheet);

        assertTrue(size < timesheetService.findAll().size());
    }

    //@Test
    public void testUpdate() {
        Timesheet timesheet = newTimesheet();
        timesheetService.save(timesheet);

        timesheet.setHours(40);
        taskService.save(timesheet.getTask());
        timesheetService.save(timesheet);

        Timesheet found = timesheetService.findById(timesheet.getId());
        assertTrue(40 == found.getHours());
    }

    //@Test
    public void testFind() {
        Timesheet timesheet = newTimesheet();
        timesheetService.save(timesheet);

        assertEquals(timesheet, timesheetService.findById(timesheet.getId()));
    }

    //@Test
    public void testList() {
        assertEquals(0, timesheetService.findAll().size());
        Timesheet templateTimesheet = new Timesheet();

        List<Timesheet> timesheets = Arrays.asList(
          newTimesheetFromTimeTemplate(templateTimesheet, 10),
          newTimesheetFromTimeTemplate(templateTimesheet, 20),
          newTimesheetFromTimeTemplate(templateTimesheet, 30)
        );

        for(Timesheet timesheet: timesheets) {
            timesheetService.save(timesheet);
        }

        List<Timesheet> found = timesheetService.findAll();
        assertEquals(3, found.size());
        for(Timesheet timesheet: found) {
            assertTrue(timesheets.contains(timesheet));
        }
    }

    //@Test
    public void testRemove() {
        Timesheet timesheet = newTimesheet();
        timesheetService.save(timesheet);

        assertEquals(timesheet, timesheetService.findById(timesheet.getId()));

        timesheetService.delete(timesheet);
        assertNull(timesheetService.findById(timesheet.getId()));
    }

    private Timesheet newTimesheet() {
        return new Timesheet(employee, task, 20);
    }

    private Timesheet newTimesheetFromTimeTemplate(Timesheet template, Integer hours) {
        return new Timesheet(
                template.getEmployee(),
                template.getTask(),
                hours
        );
    }
}
