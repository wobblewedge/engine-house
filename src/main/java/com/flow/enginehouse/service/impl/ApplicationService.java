package com.flow.enginehouse.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.service.ProcessWorkflowService;


@Service
public class ApplicationService{
	@Autowired
	private ProcessWorkflowService service;
    ObjectMapper objMap = new ObjectMapper();

	private static Map<String,Object> details = new HashMap<>();

	
	public Map<String,Object> instantiateProcess(Applicant applicant) {	
		details = objMap.convertValue(applicant, Map.class);
		service.manageDeployment();
		details.putAll(service.startProcess(applicant));
	    service.updateApproval(details);
	    return details;
		}
	
	
	public Map<String,Object> getUserTasks(){
	String assignee= (String) details.get("userId");
	System.out.println(assignee);
	return service.getTasks(assignee);
	}


	public Map<String,Object> retrieveActiveProcesses() {
		List<ProcessDefinition> activeList= service.retrieveActiveProcesses();
		Map<String,Object> activeProcesses = new HashMap<>();
		for(ProcessDefinition pd: activeList) {
			activeProcesses.put("ProcessDefinition", objMap.convertValue(pd, Map.class));
		}
		return activeProcesses;
	}
	
	public JSONArray retrieveActiveProcessInstances() {
		List<ProcessInstance> activeInstances = service.retrieveActiveInstances();
		JSONArray json = new JSONArray();
		for(ProcessInstance pi: activeInstances) {
			Map<String,Object> deetMap = objMap.convertValue(pi, Map.class);
			json.put(deetMap);
		}
		return json;
	}
	
	
	


	
	/*public List<Applicant> getApplications() {
		//stream all applicants in repo into a 
		return applicantRepo.findAll().stream().map(ApplicantConverter::entityToDto).collect(Collectors.toList());
	}

	
	public ApplicantDto getApplicantById(Long id) {
		return ApplicantConverter.entityToDto(applicantRepo.getOne(id));
	}*/
}
;;