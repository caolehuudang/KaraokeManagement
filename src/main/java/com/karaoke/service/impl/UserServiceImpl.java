package com.karaoke.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.karaoke.bo.UserDTO;
import com.karaoke.bo.UserProfileMessage;
import com.karaoke.common.Contants;
import com.karaoke.dao.UserDao;
import com.karaoke.model.User;
import com.karaoke.service.UserService;

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
			userOld.setRole(user.getRole());
			
			userDao.save(userOld);
			return new UserProfileMessage(Contants.SUCCESSFULLY, userOld);
		}else {
			return new UserProfileMessage(Contants.PASSWORD_INCORRECT, null);
		}	
		
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
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
		user.setUsername(user.getUsername().strip());
		user.setPhone(user.getPhone().strip());
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
		userOld.setPhone(user.getPhone().strip());
		userOld.setRole(user.getRole());
		
		userDao.save(userOld);
		
		return new UserProfileMessage(Contants.SUCCESSFULLY, userOld);
	}

	@Override
	public List<User> search(String txtSearch) {
		return userDao.search(txtSearch);
	}

	@Override
	public Boolean isDuplicatePhone(UserDTO user) {
		if(user.getId() == null) {
			User u = userDao.findUserByPhone(user.getPhone().strip());
			return u != null;
		}else {
			User u1 = userDao.findUserDuplicatePhone(user.getId(), user.getPhone().strip());
			return u1 != null;
		}
	}
	
}
