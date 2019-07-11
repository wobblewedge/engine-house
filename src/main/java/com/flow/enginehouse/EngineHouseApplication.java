package com.flow.enginehouse;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.flow.*")
public class EngineHouseApplication {
	
	public static void main(String[] args) {

		SpringApplication.run(EngineHouseApplication.class, args);
	}
	
}
