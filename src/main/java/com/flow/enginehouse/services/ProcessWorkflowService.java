package com.flow.enginehouse.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;
import org.flowable.engine.FormService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.app.AppModel;
import org.flowable.engine.form.FormData;
import org.flowable.engine.form.StartFormData;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormDefinition;
import org.flowable.form.api.FormModel;
import org.flowable.form.api.FormRepositoryService;
import org.flowable.form.engine.FormEngine;
import org.flowable.form.engine.FormEngines;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flow.enginehouse.model.Applicant;
import com.flow.enginehouse.model.Approval;
import com.flow.enginehouse.model.TaskRepresentation;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.ArrayList;

@Service
public class ProcessWorkflowService {
 
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private FormService formService;
    @Autowired
    private FormRepositoryService formRepoService;
    @Autowired
    private FormEngine formEngine;
	@Autowired
	HistoryService historyService;
	
	private ProcessInstance instance;
    @Transactional
    public void manageDeployment() {
    	String formPath = "forms/loan-app.form";
    	String processPath = "processes/loan-2-app.bpmn20.xml";
    	//deploy process repository
 		Deployment deployment = repositoryService.createDeployment()
 		  .addClasspathResource(processPath)
 		  .deploy();
 		//deploy form repository
 		formRepoService.createDeployment()
 		    .addClasspathResource(formPath)
 		    .deploy();
    }
    
    
    @Transactional
    public String startProcess(Applicant applicant) {
    	Map<String, Object> variables = new HashMap<>();
        //Map each property that will be collected by the form.
       // formEngine.getFormService().
        variables.put("id", applicant.getId());
        variables.put("name", applicant.getFullName());
        variables.put("age", applicant.getAge());
        variables.put("address", applicant.getAddress());
        variables.put("assets", applicant.getAssets());
        variables.put("debts", applicant.getDebts());
        variables.put("credit", applicant.getCredit());

       //ProcessInstance pi = runtimeService.startProcessInstanceWithForm("loan-application", String outcome, map, String processInstanceName);
       // runtimeService.startProcessInstanceWithForm(processDefinitionId, outcome, variables, processInstanceName)
       instance = runtimeService.startProcessInstanceByKey("applicant-name", variables);
       System.out.println("Number of process definitions : "
               + repositoryService.createProcessDefinitionQuery().count());
       System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
       FormData fd = formService.getStartFormData(instance.getProcessDefinitionId());
        FormModel form = (FormModel) formService.getRenderedStartForm(instance.getProcessDefinitionId());
        historyService = processEngine.getHistoryService();
        System.out.println("Number of tasks after process start: "
                + taskService.createTaskQuery().count());
        
        return instance.getRootProcessInstanceId();
    }
    
    
    
    //Method used to track time elapsed during activity aspects of process.
    public void writeHistory() {
    	
    
    	List<HistoricActivityInstance> activities =
    	          historyService.createHistoricActivityInstanceQuery()
    	           .processInstanceId(instance.getId())
    	           .finished()
    	           .orderByHistoricActivityInstanceEndTime().asc()
    	           .list();

    	        for (HistoricActivityInstance activity : activities) {
    	          System.out.println(activity.getActivityId() + " took "
    	            + activity.getDurationInMillis() + " milliseconds");
    	        }
    }
  
    @Transactional
    public List<Task> getTasks(String assignee) {
       List<Task> task= taskService.createTaskQuery()
          .taskAssignee(assignee)
          .active()
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