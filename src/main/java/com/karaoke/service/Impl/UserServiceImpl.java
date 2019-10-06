package com.karaoke.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karaoke.model.User;
import com.karaoke.repository.UserDao;
import com.karaoke.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> getAllUser() {
		return userDao.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}

}
