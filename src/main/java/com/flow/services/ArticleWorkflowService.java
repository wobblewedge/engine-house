package com.flow.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.flowable.engine.FormService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormModel;
import org.flowable.form.engine.FormEngine;
import org.flowable.form.engine.FormEngines;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import com.flow.model.Approval;
import com.flow.model.Applicant;
import com.flow.model.TaskRepresentation;

@Service
public class ArticleWorkflowService {
 
    ProcessEngine processEngine = ProcessEngineConfiguration
    		.createStandaloneInMemProcessEngineConfiguration()
    		.buildProcessEngine();
    
    
    IdentityService identityService = processEngine.getIdentityService();
    
    TaskService taskService = processEngine.getTaskService();
    
    RepositoryService repositoryService = processEngine.getRepositoryService();

    RuntimeService runtimeService = processEngine.getRuntimeService();
    
    @Transactional
    public void createDeployment() {
    	Deployment deployment = repositoryService.createDeployment()
    			  .addClasspathResource("processes/loan-application.bpmn20.xml")
    			  .deploy();
    	System.out.println(deployment.getKey());
    }
    @Transactional
    public void startProcess(Applicant applicant) {
        Map<String, Object> theApplicant = new HashMap<>();
        //Map each property that will be collected by the form.
       // formEngine.getFormService().
        theApplicant.put("id", applicant.getId());
        theApplicant.put("name", applicant.getFullName());
        theApplicant.put("address", applicant.getAddress());
        theApplicant.put("age", applicant.getAge());
        theApplicant.put("income", applicant.getIncome());
        theApplicant.put("assets", applicant.getAssets());
        theApplicant.put("debts", applicant.getDebts());
        theApplicant.put("credit", applicant.getCredit());
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("loan-application", theApplicant);
         
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