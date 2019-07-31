package com.flow.enginehouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.flow.enginehouse.util.*;
import com.flow.enginehouse.dto.ApplicantDto;
import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.entity.ApplicantRepository;
import com.flow.enginehouse.service.AppService;
import com.flow.enginehouse.service.ProcessWorkflowService;
import com.flow.enginehouse.service.SendMailService;
/**
 * Created by ashish on 13/5/17.
 */
@RequestMapping("/applicants")
@RestController
public class ApplicantController {
	@Autowired
	AppService appService;
	@Autowired
	private ProcessWorkflowService service;
	@Autowired ApplicantRepository applicantRepo;
	
	@RequestMapping(Constants.GET_APPLICANT_BY_ID)
	public ApplicantDto getUserById(@PathVariable Long id) {
		return appService.getApplicantById(id);
	}
	
	@RequestMapping(Constants.GET_ALL_USERS)
	public List<ApplicantDto> getAllUsers() {
		return appService.getAllUsers();
	}
	
	@RequestMapping(value= Constants.SAVE_USER, method= RequestMethod.POST)
	public Map<String,Object> saveUser(@RequestBody ApplicantDto applicantDto) {
		System.out.println("Process started.");
		Map<String,Object> details = new HashMap();
		Applicant applicant= appService.saveUser(applicantDto);
		service.manageDeployment();
		details.putAll(service.startProcess(applicant));
		String applicantId = applicant.getId().toString();
	    applicant = applicantRepo.getOne(Long.valueOf(applicantId));
	    applicant.setApproval(SendMailService.approval);
	    applicant.setId(SendMailService.id);
	    applicantRepo.saveAndFlush(applicant);
	    details.put("Applicant Id: ", applicantId);
	    details.put("Applicant Info:" , applicant);
	    return details;
	}
}
