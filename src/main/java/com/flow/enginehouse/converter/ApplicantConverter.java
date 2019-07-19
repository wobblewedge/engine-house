package com.flow.enginehouse.converter;

import java.util.stream.Collectors;

import com.flow.enginehouse.dto.ApplicantDto;
import com.flow.enginehouse.entity.Applicant;

/**
 * Created by ashish on 13/5/17.
 */
public class ApplicantConverter {
	public static Applicant dtoToEntity(ApplicantDto appDto) {
		Applicant applicant = new Applicant(appDto.getName(), appDto.getAddress(), appDto.getAge(), appDto.getIncome(), appDto.getAssets(), appDto.getDebts(), appDto.getCredit());
		applicant.setId(appDto.getId());
		return applicant;
	}

	public static ApplicantDto entityToDto(Applicant applicant) {
		ApplicantDto applicantDto = new ApplicantDto(applicant.getId(), applicant.getName(), applicant.getAddress(), applicant.getAge(), applicant.getIncome(), applicant.getAssets(), applicant.getDebts(), applicant.getCredit());
		return applicantDto;
	}
}
