package com.flow.controller;

import java.util.ArrayList;
import java.util.List;

import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flow.model.Approval;
import com.flow.model.Article;
import com.flow.model.TaskRepresentation;
import com.flow.services.ArticleWorkflowService;


@RestController
public class ArticleWorkflowController {

@Autowired
private ArticleWorkflowService service;


@PostMapping("/submit")
public void submit (@RequestBody Article article) {
	System.out.println("Process started.");
	service.startProcess(article);
}

@GetMapping("/tasks")
public List<Task> getTasks(@RequestParam String assignee) {
	System.out.println("yoyoyo yoyoy yoyo yo bruh");
    return service.getTasks(assignee);
}

@PostMapping("/review")
public void review(@RequestBody Approval approval) {
    service.submitReview(approval);
}

@GetMapping("/home")
public String home() {
	return "Hello, ";
}

@RequestMapping(value="/tasks", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
public List<TaskRepresentation> getTask(@RequestParam String assignee) {
    List<Task> tasks = service.getTasks(assignee);
    List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
    for (Task task : tasks) {
        dtos.add(new TaskRepresentation(task.getId(), task.getName()));
    }
    return dtos;
}

static class TaskRepresentation {

    private String id;
    private String name;

    public TaskRepresentation(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
}
