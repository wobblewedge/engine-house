package com.flow.enginehouse.util;

public interface Constants {
	//Endpoints
	static final String GET_APPLICANT_BY_ID = "/get/{id}";
	static final String GET_ALL_USERS = "/getAll";
	static final String SAVE_USER = "/save";
	static final String ROOT_API = "/api/bpm/loanApproval";
	static final String SUBMIT_PROCESS = "/process/startProcess";
	static final String RETRIEVE_TASKS = "/task/retrieveTasks";
	static final String RETRIEVE_PROCESSES = "/retrieveProcesses/{userId}";
	static final String ALL_PROCESSES = "/process/retrieveActiveProcesses";
	static final String COMPLETE_TASK = "/task/completeTask";
	
	//Loan Possibilities
	static final String LOAN_APPROVED = "APPROVED";
	static final String LOAN_REJECTED = "REJECTED";
	static final String LOAN_PROGRESS = "IN_PROGRESS";
	
}
