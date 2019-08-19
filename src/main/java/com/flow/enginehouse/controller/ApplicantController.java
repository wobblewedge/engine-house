package com.flow.enginehouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flow.enginehouse.util.*;
import com.sun.mail.iap.Response;
import com.flow.enginehouse.dto.ApplicantDto;
import com.flow.enginehouse.entity.ApplicantRepository;
import com.flow.enginehouse.service.AppService;

@RequestMapping(Constants.ROOT_API)
@RestController
public class ApplicantController {
	@Autowired
	AppService appService;
	@Autowired
	ApplicantRepository applicantRepo;

	@RequestMapping(Constants.RETRIEVE_PROCESSES)
	public ApplicantDto getUserById(@PathVariable Long id) {
		return appService.getApplicantById(id);
	}
	
	/*@RequestMapping("repository/process-defn=initions?key=loan-2-app.bpm20.xml")
	public String getProcessDefinitions() {
		ResponseBody response = new Response();
		return 
	}*/
	

	@RequestMapping("")
	public List<ApplicantDto> getAllUsers() {
		return appService.getAllUsers();
	}

	@RequestMapping(value = Constants.SUBMIT_PROCESS, method = RequestMethod.POST)
	public Map<String, Object> saveUser(@RequestBody ApplicantDto applicantDto) {
		return appService.saveUser(applicantDto);
	}
}
