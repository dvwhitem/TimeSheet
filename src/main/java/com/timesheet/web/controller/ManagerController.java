package com.timesheet.web.controller;


import com.timesheet.domain.Manager;
import com.timesheet.service.ManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.*;


/**
 * @author Yan
 *         Date: 5/26/15
 *         Time: 2:44 PM
 */
@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    @RequestMapping("/managers/{pageNumber}")
    public Page<Manager> showManagers(@PathVariable Integer pageNumber) {
        return managerService.findAll(new PageRequest(pageNumber -1, 2, Sort.Direction.DESC, "id"));
    }


    @RequestMapping("/manager/{id}")
    public Manager showManagerById(@PathVariable Long id) {
        return managerService.findById(id);
    }


}
