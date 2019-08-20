package com.flow.enginehouse.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.service.ProcessWorkflowService;


@Service
public class ApplicationService{
	@Autowired
	private ProcessWorkflowService service;

	
	public Map<String,Object> instantiateProcess(Applicant applicant) {
		Map<String,Object> details = new HashMap<>();
		if(applicant==null) {
			throw new ResourceNotFoundException("Empty Request Body Not Allowed");
		}else {
		service.manageDeployment();
		details.putAll(service.startProcess(applicant));
//	    service.updateApproval(applicant);
	    details.put("Applicant Info:" , applicant);
		return details;
		}
	}
	


	
	/*public List<Applicant> getApplications() {
		//stream all applicants in repo into a 
		return applicantRepo.findAll().stream().map(ApplicantConverter::entityToDto).collect(Collectors.toList());
	}

	
	public ApplicantDto getApplicantById(Long id) {
		return ApplicantConverter.entityToDto(applicantRepo.getOne(id));
	}*/
}