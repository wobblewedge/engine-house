package com.flow.enginehouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flow.enginehouse.util.*;
import com.flow.enginehouse.dto.ApplicantDto;
import com.flow.enginehouse.entity.ApplicantRepository;
import com.flow.enginehouse.service.AppService;

@RequestMapping("/applicants")
@RestController
public class ApplicantController {
	@Autowired
	AppService appService;
	@Autowired
	ApplicantRepository applicantRepo;

	@RequestMapping(Constants.GET_APPLICANT_BY_ID)
	public ApplicantDto getUserById(@PathVariable Long id) {
		return appService.getApplicantById(id);
	}

	@RequestMapping(Constants.GET_ALL_USERS)
	public List<ApplicantDto> getAllUsers() {
		return appService.getAllUsers();
	}

	@RequestMapping(value = Constants.SAVE_USER, method = RequestMethod.POST)
	public Map<String, Object> saveUser(@RequestBody ApplicantDto applicantDto) {
		return appService.saveUser(applicantDto);
	}
}
