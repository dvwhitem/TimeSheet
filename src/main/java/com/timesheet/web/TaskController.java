package com.timesheet.web;

import com.timesheet.domain.Task;
import com.timesheet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yan on 5/15/15.
 */

@RestController
public class TaskController {

@Autowired
private TaskService taskService;

@RequestMapping("/tasks/{pageNumber}")

public Page<Task> showTasks(@PathVariable Integer pageNumber) {
    return taskService.findAll(new PageRequest(pageNumber -1, 2, Sort.Direction.DESC, "id"));
}


@RequestMapping("/task/{id}")
public Task showTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }


}
