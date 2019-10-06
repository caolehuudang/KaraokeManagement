package com.karaoke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karaoke.model.User;
import com.karaoke.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/hi")
	public String sayHi() {
		return "hello";
	}
	
	@GetMapping("/getAllUser")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	@PostMapping("/saveOrUpdateUser")
	public User saveOrUpdateUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
}
