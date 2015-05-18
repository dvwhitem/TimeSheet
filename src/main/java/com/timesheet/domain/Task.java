package com.timesheet.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vitaliy on 09.04.15.
 */
@Entity
@Table(name = "task")
public class Task {

    private Long id;

    private List<Employee> assignedEmployees = new ArrayList<Employee>();

    private Manager manager;

    private String description;

    private Boolean completed;

    public  Task() {}

    public Task(String description, Manager manager, Employee... employees) {
        this.description = description;
        this.manager = manager;
        assignedEmployees.addAll(Arrays.asList(employees));
        completed = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "task_employee",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(List<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public void addEmployee(Employee e) {
        assignedEmployees.add(e);
    }

    public void removeEmployee(Employee e) {
        assignedEmployees.remove(e);
    }

    @OneToOne
    @JoinColumn(name = "manager_id")
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        if (completed != task.completed) {
            return false;
        }
        if (description != null ? !description.equals(task.description) : task.description != null) {
            return false;
        }
        if (id != null ? !id.equals(task.id) : task.id != null) {
            return false;
        }
        if (manager != null ? !manager.equals(task.manager) : task.manager != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (completed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", assignedEmployees=" + assignedEmployees +
                ", manager=" + manager +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}
