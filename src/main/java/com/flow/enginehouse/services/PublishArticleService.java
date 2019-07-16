package com.flow.enginehouse.services;


import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class PublishArticleService implements JavaDelegate {
	
	private Expression text;
	
	  public void execute(DelegateExecution execution) {
	    int credit = (Integer) execution.getVariable("credit");
	    Boolean approval = credit >= 600 ? true : false;
	    execution.setVariable("approval", approval);
	  }
	}
