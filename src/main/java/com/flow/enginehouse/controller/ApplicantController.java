package com.flow.enginehouse.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Provider.Service;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.service.ProcessWorkflowService;
import com.flow.enginehouse.service.impl.ApplicationService;
import com.flow.enginehouse.util.*;

@RequestMapping(Constants.ROOT_API)
@RestController
public class ApplicantController {
	@Autowired
	ApplicationService applicationService;
	@Autowired
	ProcessWorkflowService processWorkflowService;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	ProcessEngine processEngine;
	@Autowired
	RuntimeService runtimeService;
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
	
	//@RequestMapping(value = Constants.ALL_PROCESSES, method = RequestMethod.GET)
	public Map<String,Object> getActiveProcesses(){
		return applicationService.retrieveActiveProcesses();
	}
	
	@RequestMapping(value=Constants.RETRIEVE_TASKS, method=RequestMethod.GET)
	public Map<String,Object> assigneeTasks(){
		return	applicationService.getUserTasks();
	}
	
	@RequestMapping(value=Constants.COMPLETE_TASK, method=RequestMethod.POST, produces="application/json")
	public void completeCurrentTask(@PathVariable String taskId) {
		processWorkflowService.completeTask(taskId);
	}
	
	//@RequestMapping(value = "/rest/process-definitions", method = RequestMethod.GET, produces = "application/json")
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
	
	@RequestMapping(value="process/retrieveActiveProcesses", method=RequestMethod.GET, produces= "application/json")
	public JSONObject processDeployments() throws IOException{
		URL url = new URL("http://localhost:8081/api/bpm/loanApproval/rest/process-definitions");
		StringBuilder sb = new StringBuilder();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().active();
		List<ProcessDefinition> data = processDefinitionQuery.listPage(0, 10);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		try {
		if (conn.getResponseCode() >= 300) {
			System.out.println("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
		String output = "";		
		System.out.println("Output from Server .... \n");
		
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			sb.append(output);
		}
		conn.disconnect();
	  } catch (MalformedURLException e) {
		e.printStackTrace();

	  }
		JSONObject json = new JSONObject(sb.toString());
		return json;

	}
	
	 @GetMapping("/latest-definitions")
	    public List latestDefinitions() {
	        return repositoryService.createProcessDefinitionQuery()
	            .latestVersion()
	            .list()
	            .stream()
	            .map(ProcessDefinition::getKey)
	            .collect(Collectors.toList());
	    }

	
	@RequestMapping(value="/runtime/process-instances", method = RequestMethod.GET)
	public List processInstances() {
		ObjectMapper objMap = new ObjectMapper();
		return processWorkflowService.retrieveActiveInstances()
				
				.stream()
				.map(ProcessInstance::getId)
				.collect(Collectors.toList());
		
	}
	
}

