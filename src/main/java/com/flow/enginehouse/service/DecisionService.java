package com.flow.enginehouse.service;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import com.flow.enginehouse.util.Constants;

public class DecisionService implements JavaDelegate {
	@Autowired
	ProcessWorkflowService service;
	
	public static String loanDecision;
	public static Integer apr;
	public static String userId;
	
	//In xml, this is the flowable:expression with embedded variables. Can alter them here.
	private Expression message;
	@Override
    public void execute(DelegateExecution execution) {
        int credit = (Integer)execution.getVariable("creditScore");
        userId = (String) execution.getVariable("userId");
        loanDecision = credit >= 700 ? Constants.LOAN_APPROVED : credit >=600 ? Constants.LOAN_PROGRESS: Constants.LOAN_REJECTED;
        apr = (int) (loanDecision == Constants.LOAN_REJECTED ? 0 : 6.0);
        
        try {
        execution.setVariable("loanDecision", loanDecision, false);
        execution.setVariable("apr", loanDecision, false);
        execution.setVariable("processId", ProcessWorkflowService.instanceId);
        }catch(Exception e) {
        	System.out.println("Entity Not Found");
        }
    }
	
}