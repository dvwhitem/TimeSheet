package com.timesheet.web;

import com.timesheet.domain.Employee;
import com.timesheet.service.EmployeeService;
import com.timesheet.web.helpers.EntityGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.PostConstruct;
import java.util.List;

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

    @RequestMapping("/employees/{pageNumber}")
    public Page<Employee> showEmployees(@PathVariable Integer pageNumber) {
        return employeeService.findAll(new PageRequest(pageNumber -1, 2, Sort.Direction.DESC, "id"));
    }
}
