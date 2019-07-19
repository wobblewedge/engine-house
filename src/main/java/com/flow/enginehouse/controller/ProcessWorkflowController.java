package com.flow.enginehouse.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.flowable.engine.FormService;
import org.flowable.engine.form.FormProperty;
import org.flowable.engine.form.StartFormData;
import org.flowable.form.api.FormModel;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.entity.Approval;
import com.flow.enginehouse.service.ProcessWorkflowService;

@RequestMapping("/loan")
@RestController
public class ProcessWorkflowController {

@Autowired
private ProcessWorkflowService service;


@PostMapping("/submit")
public String submit (@RequestBody Applicant applicant) {
	System.out.println("Process started.");
	service.manageDeployment();
	return service.startProcess(applicant);
}

@GetMapping("/tasks")
public List<Task> getTasks(@RequestParam String assignee) {
	System.out.println("Tasks as follows:");
    return service.getTasks(assignee);
}

@GetMapping("/home")
public List<FormProperty> getForm(ModelMap model) {
	return service.getForm();
}

@GetMapping("/applicant")
public Map<String,Object> getInformation(){
	return service.getInfo();
}

@PostMapping("/review")
public void review(@RequestBody Approval approval) {
    service.submitReview(approval);
}

@GetMapping("/history")
public Map<String,String> history() {
return service.writeHistory();
}
}

