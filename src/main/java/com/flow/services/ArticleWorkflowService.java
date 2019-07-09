package com.flow.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import com.flow.model.Approval;
import com.flow.model.Article;
import com.flow.model.TaskRepresentation;


@Service
public class ArticleWorkflowService {
    @Autowired
    private RuntimeService runtimeService;
  
    @Autowired
    private TaskService taskService;
 
    @Transactional
    public void startProcess(Article article) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("author", article.getAuthor());
        variables.put("url", article.getUrl());
        runtimeService.startProcessInstanceByKey("loan-application", variables);
        
    }
  
    @Transactional
    public List<Task> getTasks(String assignee) {
       List<Task> task= taskService.createTaskQuery()
          .taskAssignee(assignee)
          .list();
       return task;
       
    }
  

    @Transactional
    public void submitReview(Approval approval) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("approved", approval.getStatus());
        taskService.complete(approval.getId(), variables);
    }
    
    
}