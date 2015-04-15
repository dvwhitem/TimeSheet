package com.timesheet.service.impl;

import com.google.common.collect.Lists;
import com.timesheet.domain.Employee;
import com.timesheet.domain.Task;
import com.timesheet.domain.Timesheet;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vitaliy on 10.04.15.
 */
@Service("employeeService")
@Repository
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return Lists.newArrayList(employeeRepository.findAll());
    }

    public Employee findById(Long id) {
        return employeeRepository.findOne(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    public boolean deleteEmployee(Employee employee) {

        TypedQuery employeeTaskQuery = 
                entityManager.createQuery(
                        "select t from Task t where :id in elements(t.assignedEmployees)", 
                        Task.class);
        employeeTaskQuery.setParameter("id", employee.getId());
        if(!employeeTaskQuery.getResultList().isEmpty()) {
            return false;
        }

        TypedQuery employeeTimesheetQuery =
                entityManager.createQuery(
                        "select t from Timesheet t where t.employee.id = :id",
                        Timesheet.class);
        employeeTimesheetQuery.setParameter("id", employee.getId());

        if(!employeeTimesheetQuery.getResultList().isEmpty()) {
            return false;
        }

        delete(employee);
        return true;
    }
}
