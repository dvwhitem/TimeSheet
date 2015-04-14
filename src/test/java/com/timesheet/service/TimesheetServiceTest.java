package com.timesheet.service;

import com.timesheet.domain.Employee;
import com.timesheet.domain.Manager;
import com.timesheet.domain.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by vitaliy on 14.04.15.
 */
@ContextConfiguration(locations = "/persistence-beans.xml")
public class TimesheetServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TimesheetService timesheetService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ManagerService managerService;

    @Before
    public void insertData() {
        executeScript("sql/create-data.sql");
    }

    @After
    public void cleanUp() {
        executeScript("sql/cleanup.sql");
    }

    //@Test
    public void testBusiestTask() {
        Task task = timesheetService.busiestTask();
        assertTrue(1 == task.getId());
    }

    //@Test
    public void testTasksForEmployee() {
        Employee carlos = employeeService.findById(1l);
        Employee owen = employeeService.findById(2l);

        assertEquals(2, timesheetService.tasksForEmployee(carlos).size());
        assertEquals(1, timesheetService.tasksForEmployee(owen).size());
    }

    @Test
    public void testTasksForManager() {
        Manager gram = managerService.findById(1L);
        assertEquals(1, timesheetService.tasksForManager(gram).size());
    }

    private void executeScript(String s) {
        ClassPathResource resource = new ClassPathResource(s);
        Connection connection = DataSourceUtils.getConnection(dataSource);
        ScriptUtils.executeSqlScript(connection, new EncodedResource(resource, "UTF-8"));
    }
}
