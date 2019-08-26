package com.flow.enginehouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	@Autowired
	RepositoryService repositoryService;
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
	
	@RequestMapping(value = Constants.ALL_PROCESSES, method = RequestMethod.GET)
	public Map<String,Object> getActiveProcesses(){
		return applicationService.retrieveActiveProcesses();
	}
	
	@RequestMapping(value=Constants.RETRIEVE_TASKS, method=RequestMethod.GET)
	public Map<String,Object> assigneeTasks(){
		return	applicationService.getUserTasks();
	}
	
	@RequestMapping(value = "/rest/process-definitions", method = RequestMethod.GET, produces = "application/json")
	public Map<String,Map<String,Object>> findProcessDefinitions(@RequestParam Map<String, String> allRequestParams) {
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().active();
		List<ProcessDefinition> data = processDefinitionQuery.listPage(0, 10);
		Map<String,Map<String,Object>> processDetails = new HashMap<>();
		Map<String,Object> individualDetails = new HashMap<>();

		for(ProcessDefinition pd: data) {
			individualDetails.put("ID", pd.getId());
			individualDetails.put("Name", pd.getName());
			individualDetails.put("Tenant Id", pd.getTenantId());

			processDetails.put("ProcessDefinition:", individualDetails);
			
		}
		return processDetails;
	}
}

