package com.mobile.store.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.store.bo.UserDTO;
import com.mobile.store.bo.UserProfileMessage;
import com.mobile.store.common.Contants;
import com.mobile.store.entity.User;
import com.mobile.store.repository.UserDao;
import com.mobile.store.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public User findById(Long id) {
		return userDao.findById(id).get();
	}

	@Override
	public List<User> getAllUser() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public UserProfileMessage updateUser(UserDTO user) {
		
		User userOld = userDao.findById(user.getId()).get();
		
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		
		Boolean valid = b.matches(user.getPassword(), userOld.getPassword());
		
		if(valid) {
			userOld.setFullName(user.getFullName());
			userOld.setEmail(user.getEmail());
			userOld.setPassword(bcryptEncoder.encode(user.getPasswordConfirm()));
			userOld.setImage(user.getImage());
			
			userDao.save(userOld);
			return new UserProfileMessage(Contants.SUCCESSFULLY, userOld);
		}else {
			return new UserProfileMessage(Contants.PASSWORD_INCORRECT, null);
		}	
		
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public User activeAccount(String username) {
		User user = findByUsername(username);
		if(user.getStatus().equals(Contants.DE_ACTIVE)) {
			user.setStatus(Contants.ACTIVE);
			userDao.save(user);
			return user;
		}else {
			return null;
		}
		
	}

	@Override
	public User addNewUser(User user) {
		user.setPhoneNumber(user.getPhoneNumber().strip());
		return userDao.save(user);
	}

	@Override
	public User editImage(MultipartFile file, Long id) throws IOException {
		User userOld = userDao.findById(id).get();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(file.getBytes(), false)));
		
        userOld.setImage(sb.toString());
        
        userDao.save(userOld);
        
		return userOld;
	}

	@Override
	public UserProfileMessage updateUserForAdmin(UserDTO user) {
		
		User userOld = userDao.findById(user.getId()).get();
		
		userOld.setEmail(user.getEmail());
		userOld.setFullName(user.getFullName());
		userOld.setPhoneNumber(user.getPhone().strip());
		
		userDao.save(userOld);
		
		return new UserProfileMessage(Contants.SUCCESSFULLY, userOld);
	}

	@Override
	public Boolean isDuplicatePhone(UserDTO user) {
		if(user.getId() == null) {
			User u = userDao.findUserByPhoneNumber(user.getPhone().strip());
			return u != null;
		}else {
			User u1 = userDao.findUserDuplicatePhone(user.getId(), user.getPhone().strip());
			return u1 != null;
		}
	}

	@Override
	public User findUserByPhoneNumber(String phone) {
		User u = userDao.findUserByPhoneNumber(phone);
		
		if(u != null) {
			//Hibernate.initialize(u.getVip());
		}
		
		return u;
	}
	
}
