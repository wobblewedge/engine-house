package com.flow.enginehouse.converter;

import java.util.stream.Collectors;

import com.flow.enginehouse.dto.ApplicantDto;
import com.flow.enginehouse.entity.Applicant;

public class ApplicantConverter {
	public static Applicant dtoToEntity(ApplicantDto appDto) {
		Applicant applicant = new Applicant(appDto.getFirstName(), appDto.getLastName(), appDto.getSSN(),appDto.getAge(), appDto.getIncome(), appDto.getLoanAmount(), appDto.getCreditScore(), appDto.getEmail(), appDto.getAddress());
		applicant.setUserId(appDto.getUserId());
		return applicant;
	}

	public static ApplicantDto entityToDto(Applicant applicant) {
		ApplicantDto applicantDto = new ApplicantDto(applicant.getFirstName(), applicant.getLastName(), applicant.getSSN(), applicant.getAge(), applicant.getIncome(), applicant.getLoanAmount(), applicant.getCreditScore(), applicant.getEmail(), applicant.getAddress());
		return applicantDto;
	}
}
