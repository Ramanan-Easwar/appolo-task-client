package com.skarva.client.controller;

import com.skarva.client.helper.TaskServiceClient;
import com.skarva.client.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class TaskController {

    TaskServiceClient taskServiceClient;

    @Autowired
    public TaskController(TaskServiceClient taskServiceClient) {
        this.taskServiceClient = taskServiceClient;
    }

    @GetMapping("/task")
    public String taskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task";
    }

    @GetMapping("/task/{id}")
    public String taskById(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskServiceClient.getTaskById(id));
        return "taskSingle";
    }

    @GetMapping("/task/all")
    public String taskAll(Model model) {
        model.addAttribute("tasks", taskServiceClient.getAllTask());
        return "taskAllFilter";
    }

    @GetMapping("/task/all/complete")
    public String taskAllComplete(Model model) {
        model.addAttribute("tasks", taskServiceClient.getAllCompleteTask());
        return "taskAllFilter";
    }

    @GetMapping("/task/all/incomplete")
    public String taskAllIncomplete(Model model) {
        model.addAttribute("tasks", taskServiceClient.getAllInCompleteTask());
        return "taskAllFilter";
    }

    @PostMapping("/task")
    public String createTask(@ModelAttribute Task task, Model model) {
        model.addAttribute("task", taskServiceClient.createTask(task));
        return "addSuccess";
    }

    @GetMapping ("/task/submit/{id}")
    public String finishTask(@PathVariable Long id, Model model) {
        String res = taskServiceClient.completeTask(id);
        System.out.println("res:"  + res);
        model.addAttribute("task", taskServiceClient.getTaskById(id));
        return "taskSingle";
    }


}
