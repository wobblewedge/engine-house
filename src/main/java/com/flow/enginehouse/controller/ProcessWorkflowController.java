package com.flow.enginehouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.flowable.engine.FormService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flow.enginehouse.model.Applicant;
import com.flow.enginehouse.model.Approval;
import com.flow.enginehouse.model.TaskRepresentation;
import com.flow.enginehouse.services.ProcessWorkflowService;


@RestController
public class ProcessWorkflowController {

@Autowired
private ProcessWorkflowService service;

@RequestMapping
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

@PostMapping("/review")
public void review(@RequestBody Approval approval) {
    service.submitReview(approval);
}

@GetMapping("/history")
public void history() {
service.writeHistory();
}

@RequestMapping(value="/task", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
public List<TaskRepresentation> getTask(@RequestParam String assignee) {
    List<Task> tasks = service.getTasks(assignee);
    List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
    for (Task task : tasks) {
        dtos.add(new TaskRepresentation(task.getId(), task.getName()));
    }
    return dtos;
	}
}
