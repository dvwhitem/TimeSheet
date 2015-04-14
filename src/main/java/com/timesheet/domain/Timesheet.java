package com.timesheet.domain;

import javax.persistence.*;

/**
 * Created by vitaliy on 09.04.15.
 */
@Entity
@Table(name = "timesheet")
public class Timesheet {

    private Long id;

    private Employee employee;

    private Task task;

    private Integer hours;

    public  Timesheet() {}

    public Timesheet(Employee employee, Task task, Integer hours) {
        this.employee = employee;
        this.task = task;
        this.hours = hours;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @OneToOne
    @JoinColumn(name = "task_id")
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getHours() {
        return hours;
    }

    public Integer alterHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timesheet timesheet = (Timesheet) o;

        if (id != null ? !id.equals(timesheet.id) : timesheet.id != null) return false;
        if (employee != null ? !employee.equals(timesheet.employee) : timesheet.employee != null) return false;
        if (task != null ? !task.equals(timesheet.task) : timesheet.task != null) return false;
        return !(hours != null ? !hours.equals(timesheet.hours) : timesheet.hours != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Timesheet{" +
                "id=" + id +
                ", employee=" + employee +
                ", task=" + task +
                ", hours=" + hours +
                '}';
    }
}
