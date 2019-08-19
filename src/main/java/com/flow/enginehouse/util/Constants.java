package com.flow.enginehouse.util;

public interface Constants {
	static final String GET_APPLICANT_BY_ID = "/get/{id}";
	static final String GET_ALL_USERS = "/getAll";
	static final String SAVE_USER = "/save";
	static final String ROOT_API = "/api/bpm/loanApproval";
	static final String SUBMIT_PROCESS = "/process/startProcess";
	static final String RETRIEVE_TAKS = "/task/retrieveTasks";
	static final String RETRIEVE_PROCESSES = "/retrieveProcesses/{id}";
	static final String COMPLETE_TASK = "/task/completeTask";
	static final String LOAN_APPROVED = "APPROVED";
	static final String LOAN_REJECTED = "REJECTED";
	static final String LOAN_PROGRESS = "IN_PROGRESS";
	
}
