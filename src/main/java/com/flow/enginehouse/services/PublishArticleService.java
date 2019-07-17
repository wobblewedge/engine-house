package com.flow.enginehouse.services;


import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class PublishArticleService implements JavaDelegate {
	
	private Expression approval;
	
	  public void execute(DelegateExecution execution) {
		  System.out.println(execution.getVariable("credit"));
	    int credit = (Integer) execution.getVariable("credit");
	    Boolean approval = credit >= 600 ? true : false;
	    execution.setVariable("approval", approval);
	  }
	}
