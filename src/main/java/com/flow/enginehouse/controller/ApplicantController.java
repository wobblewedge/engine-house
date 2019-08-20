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
import com.fasterxml.jackson.databind.util.JSONPObject;
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
	
	
	@RequestMapping("repository/models")
	public void getProcessDefinitions() {
		
	}
	

	@RequestMapping("")
	public List<ApplicantDto> getAllUsers() {
		return appService.getAllUsers();
	}

	@RequestMapping(value = Constants.SUBMIT_PROCESS, method = RequestMethod.POST)
	public Map<String, Object> saveUser(@RequestBody ApplicantDto applicantDto) {
		return appService.saveUser(applicantDto);
	}
}
