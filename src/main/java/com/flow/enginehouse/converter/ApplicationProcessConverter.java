package com.flow.enginehouse.converter;

import com.flow.enginehouse.dto.ApplicationProcessDto;
import com.flow.enginehouse.entity.ApplicationProcess;

public class ApplicationProcessConverter {
	public static ApplicationProcess dtoToEntity(ApplicationProcessDto applicationDto) {
		ApplicationProcess application = new ApplicationProcess(applicationDto.getProcessId(), applicationDto.getLoanDecision(), 
				applicationDto.getApr(), applicationDto.getTaskId(), applicationDto.getLoanDecisionBy(), applicationDto.getLoanDecisionDate(), applicationDto.getComments());
		return application;
}

	public static ApplicationProcessDto entityToDto(ApplicationProcess application) {
		ApplicationProcessDto applicationDto = new ApplicationProcessDto(application.getProcessId(), application.getLoanDecision(), application.getApr(), application.getTaskId(), application.getLoanDecisionBy(), application.getLoanDecisionDate(), application.getComments());
		return applicationDto;
	}
}
