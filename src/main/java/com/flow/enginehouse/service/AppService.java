package com.flow.enginehouse.service;

import java.util.List;

import org.springframework.boot.actuate.trace.http.HttpTrace.Response;

import com.flow.enginehouse.dto.ApplicantDto;
import com.flow.enginehouse.entity.Applicant;

public interface AppService {
    ApplicantDto getApplicantById(Long id);
    Applicant saveUser(ApplicantDto appDto);
    List<ApplicantDto> getAllUsers();
}
