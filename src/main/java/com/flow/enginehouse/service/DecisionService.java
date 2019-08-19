package com.flow.enginehouse.service;

import javax.persistence.EntityNotFoundException;

import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import com.flow.enginehouse.util.Constants;

public class DecisionService implements JavaDelegate {
	@Autowired
	ProcessWorkflowService service;
	
	public static String approval;
	public static Long id;
	public static int apr;
	
	private Expression message;
	@Override
    public void execute(DelegateExecution execution) {
        int credit = (Integer)execution.getVariable("credit");
        int income = (Integer)execution.getVariable("income");
        id = (Long) execution.getVariable("id");
        approval = credit >= 750 ? Constants.LOAN_APPROVED: credit >= 640 ? Constants.LOAN_PROGRESS : Constants.LOAN_REJECTED;
        apr = approval==Constants.LOAN_APPROVED && income > 100000 ? 6 : 15;
        try {
        execution.setVariable("approval", approval, false);
        execution.setVariable("apr", apr, false);
        }catch(EntityNotFoundException e) {
        	System.out.println("Entity Not Found");
        }
    }
	
};