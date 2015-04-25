package com.timesheet.web;

import com.timesheet.web.helpers.EntityGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

/**
 * Created by go1095 on 4/15/15.
 */
@Controller
@RequestMapping(value = "/")
public class WelcomeController {

    @Autowired
    private EntityGenerator entityGenerator;

    @PostConstruct
    public void generateDomain() {
        entityGenerator.deleteDomain();
        entityGenerator.generateDomain();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("welcome", "Welcome to Timesheet application");
        return "index";
    }
}
