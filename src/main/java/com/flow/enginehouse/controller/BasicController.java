package com.flow.enginehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {

	@RequestMapping("/index")
	public String index() {
		System.out.println("Here in the form controller");
		return "index";
	}
}
