package com.flow.enginehouse.service;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

public class DecisionService implements JavaDelegate {
	@Autowired
	ProcessWorkflowService service;
	
	public static boolean approval;
	public static Long id;
	
	//In xml, this is the flowable:expression with embedded variables. Can alter them here.
	private Expression message;
	@Override
    public void execute(DelegateExecution execution) {
        int credit = (Integer)execution.getVariable("credit");
        id = (Long) execution.getVariable("id");
        approval = credit >= 600 ? true : false;
        try {
        execution.setVariable("approval", approval, false);
        }catch(Exception e) {
        	System.out.println("Entity Not Found");
        }
    }
	
}