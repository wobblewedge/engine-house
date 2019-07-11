package com.flow.enginehouse.handler;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

public class ReviewApplication implements TaskListener {

	 @Override
	    public void notify(DelegateTask delegateTask) {
	        delegateTask.setAssignee("admin");
	        delegateTask.getProcessInstanceId();
	    }

}
