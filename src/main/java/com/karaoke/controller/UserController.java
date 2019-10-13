package com.karaoke.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karaoke.model.User;
import com.karaoke.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/getUserById")
	public User findById(@RequestParam(value = "id", required = false) Long id) {
		return userService.findById(id).get();
	}
	
}
