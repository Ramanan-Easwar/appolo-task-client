package com.skarva.client.controller;

import com.skarva.client.helper.TaskServiceClient;
import com.skarva.client.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


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

    @GetMapping("/task/{uuid}")//string
    public String taskById(@PathVariable String uuid, Model model) {
        model.addAttribute("task", taskServiceClient.getTaskById(uuid));
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

    @GetMapping ("/task/submit/{uuid}")//string
    public String finishTask(@PathVariable String uuid, Model model) {
        String res = taskServiceClient.completeTask(uuid);
        model.addAttribute("task", taskServiceClient.getTaskById(uuid));
        return "taskSingle";
    }


}
