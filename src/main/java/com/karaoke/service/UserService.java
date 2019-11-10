package com.karaoke.service;

import java.util.List;

import com.karaoke.model.User;

public interface UserService {

	User findById(Long id);
	
	User findByUsername(String username);
	
	User activeAccount(String username);
	
	List<User> getAllUser();
	
	User updateUser(User user);
	
	User addNewUser(User user);
}
