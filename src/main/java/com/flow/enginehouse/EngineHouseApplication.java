package com.flow.enginehouse;


import java.util.Arrays;

import javax.annotation.PostConstruct;

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
	
	public static void main(String[] args) {

		SpringApplication.run(EngineHouseApplication.class, args);
	}
	
	@PostConstruct
	public void setupDbWithData(){
		//revisit
		Applicant applicant= new Applicant("Mick Robbins", "100 Wynview Drive", 27, 1000000, 60000, 10000, 700);
		//applicant.setSkills(Arrays.asList(new Skill("java"), new Skill("js")));
		applicant= applicantRepo.save(applicant);
	}
}
