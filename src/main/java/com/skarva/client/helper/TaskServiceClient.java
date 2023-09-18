package com.skarva.client.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skarva.client.model.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TaskServiceClient {

    @Value("${application.port}")
    private String port;
    RestTemplate restTemplate;
    Gson gson;

    public TaskServiceClient(RestTemplate restTemplate, Gson gson) {
        this.restTemplate = restTemplate;
        this.gson = gson;
    }

    public List<Task> getAllTask() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                Constants.SERVICE_URL + port + Constants.TASK + Constants.ALL_TASK, String.class);
        return gson.fromJson(response.getBody(), new TypeToken<List<Task>>(){}.getType());
    }

    public List<Task> getAllCompleteTask() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                Constants.SERVICE_URL + port + Constants.TASK + Constants.ALL_TASK
                + Constants.DONE_TASK, String.class);
        return gson.fromJson(response.getBody(), new TypeToken<List<Task>>(){}.getType());
    }
    public List<Task> getAllInCompleteTask() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                Constants.SERVICE_URL + port + Constants.TASK + Constants.ALL_TASK
                        + Constants.PENDING_TASK, String.class);
        return gson.fromJson(response.getBody(), new TypeToken<List<Task>>(){}.getType());
    }

    public Task getTaskById(Long taskId) {
        ResponseEntity<String> response = restTemplate.getForEntity(
                Constants.SERVICE_URL + port + Constants.TASK + "/" + taskId, String.class);
        return gson.fromJson(response.getBody(), Task.class);
    }

    public String completeTask(Long taskId) {
        System.out.println("inside complete task");
        HttpEntity<String> request = new HttpEntity<>("empty");
        return restTemplate.patchForObject(
                Constants.SERVICE_URL + port + Constants.TASK + "/" + taskId + Constants.FINISH_TASK,
                request, String.class);
    }

    public Task createTask(Task task) {
        Map<String, String> map = new HashMap<>();
        map.put("taskName", task.getTaskName());
        map.put("taskStatus", task.getTaskStatus());
        HttpEntity<Map<String, String>> request = new HttpEntity<>(map);
        String response = restTemplate.postForObject(
                Constants.SERVICE_URL + port + Constants.TASK + Constants.CREATE_TASK,
                request ,String.class);
        return gson.fromJson(response, Task.class);
    }
}
