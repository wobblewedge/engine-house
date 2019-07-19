package com.flow.enginehouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;
import org.flowable.engine.FormService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.app.AppModel;
import org.flowable.engine.form.FormData;
import org.flowable.engine.form.FormProperty;
import org.flowable.engine.form.StartFormData;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormDefinition;
import org.flowable.form.api.FormModel;
import org.flowable.form.api.FormRepositoryService;
import org.flowable.form.engine.FormEngine;
import org.flowable.form.engine.FormEngines;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.entity.ApplicantRepository;
import com.flow.enginehouse.entity.Approval;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
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
	private ProcessEngine processEngine;
	@Autowired
	private FormService formService;
	@Autowired
	private FormRepositoryService formRepoService;
	@Autowired
	private FormEngine formEngine;
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
		Deployment deployment = repositoryService.createDeployment().addClasspathResource(processPath).deploy();
		// deploy form repository
		formRepoService.createDeployment().addClasspathResource(formPath).deploy();
	}

	@Transactional
	public String startProcess(Applicant applicant) {
		Map<String, Object> report = new HashMap<>();
		Map<String, Object> variables = new HashMap<>();
		// Map each property that will be collected by the form.
		// formEngine.getFormService().
		variables.put("id", applicant.getId());
		variables.put("name", applicant.getName());
		variables.put("age", applicant.getAge());
		variables.put("address", applicant.getAddress());
		variables.put("assets", applicant.getAssets());
		variables.put("debts", applicant.getDebts());
		variables.put("credit", applicant.getCredit());

		instance = runtimeService.startProcessInstanceByKey("applicant-name", variables);
		// ProcessInstance pi =
		// runtimeService.startProcessInstanceWithForm("loan-application", String
		// outcome, map, String processInstanceName);
		// runtimeService.startProcessInstanceWithForm(processDefinitionId, outcome,
		// variables, processInstanceName
		System.out
				.println("Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
		System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
		FormData fd = formService.getStartFormData(instance.getProcessDefinitionId());
		historyService = processEngine.getHistoryService();
		System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
		fd.getFormProperties();
		report.put(instance.getRootProcessInstanceId(), instance.getProcessDefinitionKey());
		return instance.getRootProcessInstanceId();
	}

	public List<FormProperty> getForm() {
		FormData fd = formService.getStartFormData(instance.getProcessDefinitionId());
		return fd.getFormProperties();

	}

	// Method used to track time elapsed during activity aspects of process.
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

	@Transactional
	public void submitReview(Approval approval) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approved", approval.getStatus());
		taskService.complete(approval.getId(), variables);
	}

}