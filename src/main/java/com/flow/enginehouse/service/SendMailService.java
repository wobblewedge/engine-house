package com.flow.enginehouse.service;

import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.flow.enginehouse.entity.ApplicantRepository;

public class SendMailService implements JavaDelegate {

	private Expression message;
	@Override
    public void execute(DelegateExecution execution) {
        String value = (String) message.getValue(execution);
        String name = (String) execution.getVariable("name");
        int credit = (Integer)execution.getVariable("credit");
        
        boolean approval = credit >= 600 ? true : false;
        Long id = (Long)execution.getVariable("id");
        System.out.println("Final Approval: "+ approval);
        try {
        execution.setVariable("approval", approval);
        }catch(EntityNotFoundException e) {
        	System.out.println("Likely story");
        }
    }
}