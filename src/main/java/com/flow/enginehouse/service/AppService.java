package com.flow.enginehouse.service;

import java.util.List;

import com.flow.enginehouse.dto.ApplicantDto;

public interface AppService {
    ApplicantDto getApplicantById(Long id);
    void saveUser(ApplicantDto appDto);
    List<ApplicantDto> getAllUsers();
}
