package com.timesheet.web.controller;

import com.timesheet.domain.Timesheet;
import com.timesheet.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vitaliy, Yan on 5/17/15.
 */

@RestController
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @RequestMapping("/timesheets/{pageNumber}")

    public Page<Timesheet> showTimesheets(@PathVariable Integer pageNumber) {
        return timesheetService.findAll(new PageRequest(pageNumber -1, 2, Sort.Direction.DESC, "id"));
    }


    @RequestMapping("/timesheet/{id}")
    public Timesheet showTaskById(@PathVariable Long id) {
        return timesheetService.findById(id);
    }
}
