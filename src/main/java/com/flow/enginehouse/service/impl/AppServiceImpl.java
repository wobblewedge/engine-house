package com.flow.enginehouse.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.enginehouse.converter.ApplicantConverter;
import com.flow.enginehouse.dto.ApplicantDto;
import com.flow.enginehouse.entity.ApplicantRepository;
import com.flow.enginehouse.service.AppService;

/**
 * Created by ashish on 13/5/17.
 */
@Service
public class AppServiceImpl implements AppService {
	@Autowired
	ApplicantRepository applicantRepo;

	@Override
	public void saveUser(ApplicantDto applicantDto) {
		applicantRepo.save(ApplicantConverter.dtoToEntity(applicantDto));
	}

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
