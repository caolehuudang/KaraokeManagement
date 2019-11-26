package com.karaoke.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.karaoke.bo.UserDTO;
import com.karaoke.common.Contants;
import com.karaoke.dao.UserDao;
import com.karaoke.model.User;
import com.karaoke.service.SendMailSevice;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	SendMailSevice sendMailSevice;
	
	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		if(user.getStatus().equals(Contants.DE_ACTIVE)) {
			throw new UsernameNotFoundException("User De-active: " + username);
		}
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}
	
	public User save(UserDTO user) throws MessagingException {
		
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setUsername(user.getUsername().strip());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(("".equals(user.getRole()) ? Contants.ROLE_CUSTOMER : user.getRole() ));
		newUser.setEmail(user.getEmail());
		newUser.setFullName(user.getFullName());
		newUser.setPhone(user.getPhone().strip());
		
		sendMailSevice.sendEmail(user.getEmail(), "", user.getFullName(), user.getUsername());
		
		newUser.setStatus(Contants.DE_ACTIVE);
		
		return userDao.save(newUser);
	}

}