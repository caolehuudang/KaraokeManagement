package com.karaoke.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.karaoke.bo.UserDTO;
import com.karaoke.bo.UserProfileMessage;
import com.karaoke.model.User;

public interface UserService {

	User findById(Long id);
	
	User findByUsername(String username);
	
	User activeAccount(String username);
	
	List<User> getAllUser();
	
	UserProfileMessage updateUser(UserDTO user);
	
	User addNewUser(User user);
	
	User editImage(MultipartFile file, Long id) throws IOException;
	
	UserProfileMessage updateUserForAdmin(UserDTO user); 
	
	List<User> search(String txtSearch);
	
}
