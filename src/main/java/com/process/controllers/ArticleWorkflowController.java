package com.process.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flow.services.ArticleWorkflowService;
import com.process.model.Approval;
import com.process.model.Article;

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
public List<Article> getTasks(@RequestParam String assignee) {
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

}
