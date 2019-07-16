package com.flow.enginehouse.handler;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

public class ReviewApplication implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) {
			execution.getVariable("");
	}

}
