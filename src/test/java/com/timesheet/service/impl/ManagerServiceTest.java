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
public class ManagerServiceTest extends DomainAwareBase {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaskService taskService;

    //@Test
    public void testAdd() {
        int size = managerService.findAll().size();
        managerService.save(new Manager("engineer"));

        assertTrue(size < managerService.findAll().size());
    }

    //@Test
    public void testUpdate() {
      Manager manager = new Manager("programmer");
        managerService.save(manager);

        Manager found = managerService.findById(manager.getId());
        assertEquals(found, manager);
    }

    //@Test
    public void testFind() {
        Manager manager = new Manager("tester");
        managerService.save(manager);

        Manager found = managerService.findById(manager.getId());
        assertEquals(found, manager);
    }

    //@Test
    public void testList() {
        assertEquals(0, managerService.findAll().size());

        List<Manager> managers = Arrays.asList(
                new Manager("test 1"),
                new Manager("test 2"),
                new Manager("test 3")
        );

        for (Manager manager: managers) {
            managerService.save(manager);
        }

        List<Manager> found = managerService.findAll();
        assertEquals(3, found.size());

        for (Manager manager: found) {
            assertTrue(managers.contains(manager));
        }
    }

    //@Test
    public void testRemove() {
        Manager manager = new Manager("team lead");
        managerService.save(manager);

        assertEquals(manager, managerService.findById(manager.getId()));

        managerService.delete(manager);
        assertNull(managerService.findById(manager.getId()));
    }

    //@Test
    public void testRemoveManager() {
        Manager manager = new Manager("Lucas Olivero da Silva");
        managerService.save(manager);

        Employee employee = new Employee("Frank Rodriguez", "team lead");
        employeeService.save(employee);

        Task task = new Task("some task", manager, employee);
        taskService.save(task);

        assertFalse(managerService.deleteManager(manager));

        taskService.delete(task);

        assertTrue(managerService.deleteManager(manager));
    }

}
