package com.karaoke.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "hello admin";
	}
	
	@GetMapping("/customer")
	public String customer() {
		return "cho Tu";
	}

}