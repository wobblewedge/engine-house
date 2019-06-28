package com.flow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flow.model.Approval;
import com.flow.model.Article;
import com.flow.service.ArticleWorkflowService;


@RestController
public class ArticleWorkflowController {

@Autowired
private ArticleWorkflowService service;
@PostMapping("/submit")
public void submit (@RequestBody Article article) {
	service.startProcess(article);
	System.out.println("Process started.");
}

@GetMapping("/tasks")
public List<Article> getTasks(@RequestParam String assignee) {
	System.out.println("Task Controller");
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

}
