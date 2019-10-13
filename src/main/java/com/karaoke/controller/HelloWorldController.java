package com.karaoke.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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
	//@PreAuthorize("hasRole('ADMIN')")
	public String admin() {
		return "hello admin";
	}
	
	@GetMapping("/customer")
	//@PreAuthorize("hasRole('CUSTOMER')")
	public String customer() {
		return "cho Tu";
	}

}