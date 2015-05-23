package com.timesheet.web.controller;

import com.timesheet.domain.Task;
import com.timesheet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Vitaliy, Yan on 5/15/15.
 */

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/tasks/{pageNumber}")

    public Page<Task> showTasks(@PathVariable Integer pageNumber) {
        return taskService.findAll(new PageRequest(pageNumber - 1, 2, Sort.Direction.DESC, "id"));
    }


    @RequestMapping("/task/{id}")
    public Task showTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @RequestMapping("/taskedit/{id}")
    public Task editTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }


    @RequestMapping(value = "/taskadd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String taskAdd(@ModelAttribute("task")
                          Task task, BindingResult result) {
       if (null == task.getId()) {
          taskService.addTask(task);


        } /*else {
            taskService.updateTask(task);
        }*/
        return "redirect:/index";
    }


    @RequestMapping(value = "/taskdelete/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String taskDelete(
            @PathVariable("id") Long taskId) {



        taskService.delete(taskService.findById(taskId));

       // taskService.deleteTask(taskId);

        return "redirect:/tasks/1";
    }





    @RequestMapping(value = "/taskedit/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String taskEdit(
            @PathVariable("id") Long taskId,
            Map<String, Object> map) {
        map.put("task", taskService.findById(taskId));
      /*  map.put("taskList", taskService.listTasks());*/
        return "task";
    }

}
