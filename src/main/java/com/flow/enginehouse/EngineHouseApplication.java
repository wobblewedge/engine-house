package com.flow.enginehouse;


import java.util.Arrays;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.flow.enginehouse.entity.Applicant;

@SpringBootApplication
@ComponentScan("com.flow.*")
public class EngineHouseApplication {

	public static void main(String[] args) {

		SpringApplication.run(EngineHouseApplication.class, args);
	}
	
}
