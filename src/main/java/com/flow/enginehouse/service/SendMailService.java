package com.flow.enginehouse.service;

import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.entity.ApplicantRepository;

public class SendMailService implements JavaDelegate {
	@Autowired
	ProcessWorkflowService service;
	
	public static boolean approval;
	public static Long id;
	
	private Expression message;
	@Override
    public void execute(DelegateExecution execution) {
        String value = (String) message.getValue(execution);
        String name = (String) execution.getVariable("name");
        int credit = (Integer)execution.getVariable("credit");
        approval = credit >= 600 ? true : false;
        id = (Long) execution.getVariable("id");
        System.out.println(String.valueOf(id));
        try {
        execution.setVariable("approval", approval, false);
        System.out.println(execution.getVariable("approval"));
        }catch(EntityNotFoundException e) {
        	System.out.println("Likely story");
        }
    }
	
}