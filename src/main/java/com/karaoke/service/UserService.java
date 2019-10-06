package com.karaoke.service;

import java.util.List;

import com.karaoke.model.User;

public interface UserService {

	public List<User> getAllUser();
	
	public User saveUser(User user);
}
