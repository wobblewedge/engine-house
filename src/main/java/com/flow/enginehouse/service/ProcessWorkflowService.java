package com.flow.enginehouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.FormService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.form.FormData;
import org.flowable.engine.form.FormProperty;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormRepositoryService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.entity.ApplicantRepository;
import com.flow.enginehouse.entity.ApplicationProcess;

import java.util.ArrayList;

@Service
public class ProcessWorkflowService {

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private FormService formService;
	@Autowired
	private FormRepositoryService formRepoService;
	@Autowired
	HistoryService historyService;
	@Autowired
	ApplicantRepository applicantRepo;

	private ProcessInstance instance;
	private Execution execution;
	private Map<String, String> historyInfo = new HashMap<>();

	@Transactional
	public void manageDeployment() {
		String formPath = "forms/loan-app.form";
		String processPath = "processes/loan-2-app.bpmn20.xml";
		// deploy process repository
		repositoryService.createDeployment().addClasspathResource(processPath).deploy();
		// deploy form repository
		formRepoService.createDeployment().addClasspathResource(formPath).deploy();
	}

	@Transactional
	public ProcessInstance startProcess(Applicant applicant) {
		Map<String, Object> report = new HashMap<>();
		Map<String, Object> variables = new HashMap<>();
		// Map each property that will be collected by the form.
		// formEngine.getFormService().
		variables.put("id", applicant.getUserId());
		variables.put("first name", applicant.getFirstName());
		variables.put("last name", applicant.getLastName());
		variables.put("age", applicant.getAge());
		variables.put("address", applicant.getAddress());
		variables.put("loan amount", applicant.getLoanAmount());
		variables.put("ssn", applicant.getSSN());
		variables.put("credit", applicant.getCreditScore());
		variables.put("id", applicant.getUserId());
		instance = runtimeService.startProcessInstanceByKey("applicant-name", variables);
		
		System.out.println("Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
		System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
		System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
		report.put("Process Instance ID: ",instance.getRootProcessInstanceId());
		return instance;
	}
	
//	@Transactional
//	public ApplicationProcess getInstanceVariables(ProcessInstance instance) {		
//	}
	
	@Transactional
	public void updateApproval(ApplicationProcess application, Applicant applicant) {
	    application.setLoanDecision(DecisionService.approval);
	    applicant.setUserId(DecisionService.id);
	    application.setApr(DecisionService.apr);
	    applicantRepo.saveAndFlush(applicant);
	}
	
	
	public boolean getFinalStatus(String processInstanceId) {
		Map<String,Object> variables = instance.getProcessVariables();
		
		boolean approval = (boolean) variables.get("approval");
		return approval;
	}

	public List<FormProperty> getForm() {
		FormData fd = formService.getStartFormData(instance.getProcessDefinitionId());
		return fd.getFormProperties();

	}

	public Map<String, String> writeHistory() {
		if (historyService.createHistoricActivityInstanceQuery().list().isEmpty()) {
			System.out.print("Nothing here yet!");
		} else {
			List<HistoricActivityInstance> activities = historyService.createHistoricActivityInstanceQuery()
					.processInstanceId(instance.getProcessInstanceId()).finished()
					.orderByHistoricActivityInstanceEndTime().asc().list();

			for (HistoricActivityInstance activity : activities) {
				historyInfo.put("Activity ID", activity.getActivityId());
				historyInfo.put("Duration", activity.getDurationInMillis().toString());
				System.out.println("Activity ID" + activity.getActivityId() + "/n" + activity.getDurationInMillis()
						+ " milliseconds" + " type: ");
			}
		}
		return historyInfo;
	}

	@Transactional
	public List<Task> getTasks(String assignee) {
		List<Task> task = taskService.createTaskQuery().taskAssignee(assignee).list();
		return task;
	}

	@Transactional
	public Map<String, Object> getInfo() {
		Map<String, Object> applicantInfo = new HashMap<String, Object>();
		List<Execution> executionList = new ArrayList<Execution>();
		executionList.addAll(
				runtimeService.createExecutionQuery().processInstanceId(instance.getRootProcessInstanceId()).list());

		for (Execution e : executionList) {
			System.out.println(execution.getProcessInstanceId());
			applicantInfo.put(execution.getName(), execution.getProcessInstanceId());
		}
		return applicantInfo;
	}

}