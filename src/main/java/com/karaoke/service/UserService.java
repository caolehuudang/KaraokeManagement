package com.karaoke.service;

import java.util.List;

import com.karaoke.model.User;

public interface UserService {

	User findById(Long id);
	
	List<User> getAllUser();
	
	User updateUser(User user);
}
