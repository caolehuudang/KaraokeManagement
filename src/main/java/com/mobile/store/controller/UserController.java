package com.mobile.store.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.store.bo.UserDTO;
import com.mobile.store.bo.UserProfileMessage;
import com.mobile.store.entity.User;
import com.mobile.store.service.SendMailSevice;
import com.mobile.store.service.UserService;
import com.mobile.store.service.impl.JwtUserDetailsService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SendMailSevice sendMailSevice;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@PostMapping("/getUserById")
	public User findById(@RequestParam(value = "id", required = false) Long id) {
		return userService.findById(id);
	}
	
	@GetMapping("/getAllUser")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	@PostMapping(value = "/addNewUser", produces = "application/json; charset=UTF-8")
	public User addNewUser(@RequestBody UserDTO user) throws MessagingException {
		
		User userExist = userService.findByUsername(user.getUsername().strip());
		
		if(userExist == null) {
			if(userService.isDuplicatePhone(user)) {
				return null;
			}else {
				return userDetailsService.save(user);
			}
		}else {
			return null;
		}	
	}
	
	@PostMapping(value = "/updateUser", produces = "application/json; charset=UTF-8")
	public UserProfileMessage updateUser(@RequestBody UserDTO user) {
		
		if(userService.isDuplicatePhone(user)) {
			return null;
		}else {
			return userService.updateUser(user);
		}
		
	}
	
	@PostMapping(value = "/updateUserForAdmin", produces = "application/json; charset=UTF-8")
	public UserProfileMessage updateUserForAdmin(@RequestBody UserDTO user) {
		if(userService.isDuplicatePhone(user)) {
			return null;
		}else {
			return userService.updateUserForAdmin(user);
		}
		
	}
	
	
	@GetMapping("/confirm-account")
	public void confirmAccount(@RequestParam("username") String username, HttpServletResponse response) throws IOException {
		User user = userService.activeAccount(username);
		if(user != null) {
			 response.sendRedirect("http://localhost:3000");
		}else {
			 response.sendRedirect("http://localhost:3000/notfound");
		}
	}
	
	@PostMapping(value = "/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public User uploadFile(@RequestParam MultipartFile file, @RequestParam(value = "id") Long id) throws IOException {
     
        return userService.editImage(file, id); 
    }
	
	@PostMapping(value = "/findUserByPhone", produces = "application/json; charset=UTF-8")
	public User findUserByPhone(@RequestBody UserDTO user) {
		
		return userService.findUserByPhoneNumber(user.getPhone());
	} 
}
