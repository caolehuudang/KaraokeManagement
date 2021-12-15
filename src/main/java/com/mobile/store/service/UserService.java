package com.mobile.store.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.store.bo.UserDTO;
import com.mobile.store.bo.UserProfileMessage;
import com.mobile.store.entity.User;

public interface UserService {

	User findById(Long id);
	
	User findByUsername(String username);
	
	User activeAccount(String username);
	
	List<User> getAllUser();
	
	UserProfileMessage updateUser(UserDTO user);
	
	User addNewUser(User user);
	
	User editImage(MultipartFile file, Long id) throws IOException;
	
	UserProfileMessage updateUserForAdmin(UserDTO user); 
	
	Boolean isDuplicatePhone(UserDTO user);
	
	User findUserByPhoneNumber(String phone);
	
}
