package com.timesheet.web;

import com.timesheet.domain.Employee;
import com.timesheet.service.EmployeeService;
import com.timesheet.web.helpers.EntityGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * Created by vitaliy on 24.04.15.
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EntityGenerator entityGenerator;

    @PostConstruct
    public void generateDomain() {
        entityGenerator.deleteDomain();
        entityGenerator.generateDomain();
    }

    @RequestMapping("/employees")
    public List<Employee> showEmployees() {
        return employeeService.findAll();
    }
}
