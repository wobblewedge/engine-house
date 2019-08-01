package com.flow.enginehouse.service;

import java.util.List;
import java.util.Map;
import com.flow.enginehouse.dto.ApplicantDto;

public interface AppService {
    ApplicantDto getApplicantById(Long id);
    Map<String,Object> saveUser(ApplicantDto appDto);
    List<ApplicantDto> getAllUsers();
}
