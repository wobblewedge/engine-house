package com.flow.enginehouse.service;

import java.util.Map;

import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.common.engine.api.variable.VariableContainer;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.impl.delegate.ActivityBehavior;
import org.springframework.beans.factory.annotation.Autowired;

import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.entity.ApplicantRepository;

public class SendMailService implements JavaDelegate {
	@Autowired 
	ApplicantRepository applicantRepo;
	
	private Expression message;
	@Override
    public void execute(DelegateExecution execution) {
        String value = (String) message.getValue(execution);
        String name = (String) execution.getVariable("name");
        int credit = (Integer)execution.getVariable("credit");
        
        boolean approval = credit >= 600 ? true : false;
        Long id = (Long)execution.getVariable("id");
        Applicant applicant = applicantRepo.getOne(id);
        execution.setVariable("approvale", approval);
        applicant.setApproval(approval);
    }
}