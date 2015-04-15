package com.timesheet.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by go1095 on 4/15/15.
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("welcome", "Welcome to Timesheet application");
        return "index";
    }
}
