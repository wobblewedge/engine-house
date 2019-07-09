package com.flow.enginehouse;

import org.flowable.form.engine.FormEngine;
import org.flowable.form.engine.FormEngines;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.flow.*")
public class EngineHouseApplication {

	FormEngine formEngine = FormEngines.getDefaultFormEngine();
	
	public static void main(String[] args) {
		SpringApplication.run(EngineHouseApplication.class, args);
	}

}
