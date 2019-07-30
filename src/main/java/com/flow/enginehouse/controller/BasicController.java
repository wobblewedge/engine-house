package com.flow.enginehouse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

	@RequestMapping("/index")
	public String index() {
		System.out.println("Here in the form controller");
		return "index";
	}
}
