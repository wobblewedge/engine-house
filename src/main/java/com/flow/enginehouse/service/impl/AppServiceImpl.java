package com.flow.enginehouse.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.flow.enginehouse.converter.ApplicantConverter;
import com.flow.enginehouse.dto.ApplicantDto;
import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.entity.ApplicantRepository;
import com.flow.enginehouse.service.AppService;
import com.flow.enginehouse.service.ProcessWorkflowService;


@Service
public class AppServiceImpl implements AppService {
	@Autowired
	ApplicantRepository applicantRepo;
	@Autowired
	private ProcessWorkflowService service;

	@Override
	public Map<String,Object> saveUser(ApplicantDto applicantDto) {
		Map<String,Object> details = new HashMap<>();
		if(applicantDto==null) {
			throw new ResourceNotFoundException("Empty Request Body Not Allowed");
		}else {
		Applicant applicant = applicantRepo.save(ApplicantConverter.dtoToEntity(applicantDto));
		service.manageDeployment();
		ProcessInstance instance = service.startProcess(applicant);
		Map<String,Object> vars = new HashMap<>(instance.getProcessVariables());
		String applicantId = applicant.getUserId().toString();
	    applicant = applicantRepo.getOne(Long.valueOf(applicantId));
	    details.put("Applicant Info:" , applicant);
		return details;
		}
	}
	
	//NEED TO DO


	@Override
	public List<ApplicantDto> getAllUsers() {
		//stream all applicants in repo into a 
		return applicantRepo.findAll().stream().map(ApplicantConverter::entityToDto).collect(Collectors.toList());
	}

	@Override
	public ApplicantDto getApplicantById(Long id) {
		return ApplicantConverter.entityToDto(applicantRepo.getOne(id));
	}
}
