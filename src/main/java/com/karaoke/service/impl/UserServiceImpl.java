package com.karaoke.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.dao.UserDao;
import com.karaoke.model.User;
import com.karaoke.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	@Override
	public User findById(Long id) {
		return userDao.findById(id).get();
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return (List<User>) userDao.findAll();
	}

	@Override
	public User updateUser(User user) {
		
		User userOld = userDao.findById(user.getId()).get();
		
		userOld.setEmail(user.getEmail());
		userOld.setStatus(user.getStatus());
		userOld.setImage(user.getImage());
		userOld.setVip(user.getVip());
		
		userDao.save(userOld);

		return userOld;
	}

}
