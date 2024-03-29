package com.flow.enginehouse;


import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.flow.enginehouse.entity.Applicant;
import com.flow.enginehouse.entity.ApplicantRepository;

@SpringBootApplication
@ComponentScan("com.flow.*")
public class EngineHouseApplication {
	@Autowired
	ApplicantRepository applicantRepo;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	HistoryService historyService;
	
	public static void main(String[] args) {

		SpringApplication.run(EngineHouseApplication.class, args);
	}
	
	@PostConstruct
	public void setupDbWithData(){
		//revisit
		Applicant applicant= new Applicant("Mick","Robbins", 122456655, 56, 76000,200000,690,"mick.robbins@gmail.com", "75 Yankee Lane");
		//applicant.setSkills(Arrays.asList(new Skill("java"), new Skill("js")));
		applicant= applicantRepo.save(applicant);
	}
}
