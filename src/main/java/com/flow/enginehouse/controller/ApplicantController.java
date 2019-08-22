package com.flow.enginehouse.controller;

import java.util.List;
import java.util.Map;

import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flow.enginehouse.util.*;
import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.service.ProcessWorkflowService;
import com.flow.enginehouse.service.impl.ApplicationService;

@RequestMapping(Constants.ROOT_API)
@RestController
public class ApplicantController {
	@Autowired
	ApplicationService applicationService;
	@Autowired
	ProcessWorkflowService processWorkflowService;
	
/*	@RequestMapping(Constants.RETRIEVE_PROCESSES)
	public Applicant getUserById(@PathVariable Long id) {
		return applicationService.getApplicationId(id);
	}
	*/
	
	
	@RequestMapping("repository/models")
	public void getProcessDefinitions() {
		
	}
	

	/*@RequestMapping("")
	public List<Applicant> getAllUsers() {
		return applicationService.getAllUsers();
	}*/

	@RequestMapping(value = Constants.SUBMIT_PROCESS, method = RequestMethod.POST)
	public Map<String, Object> saveUser(@RequestBody Applicant applicant) {
		return applicationService.instantiateProcess(applicant);
	}
	
	@RequestMapping(value=Constants.RETRIEVE_TASKS, method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Task> assigneeTasks(){
		return	applicationService.getUserTasks();
	}
}

