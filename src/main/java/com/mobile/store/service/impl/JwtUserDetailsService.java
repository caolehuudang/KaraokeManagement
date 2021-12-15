package com.mobile.store.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

import com.mobile.store.bo.UserDTO;
import com.mobile.store.common.Contants;
import com.mobile.store.entity.User;
import com.mobile.store.repository.UserDao;
import com.mobile.store.service.SendMailSevice;

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
		User user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		if(user.getStatus().equals(Contants.DE_ACTIVE)) {
			throw new UsernameNotFoundException("User De-active: " + username);
		}
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		//grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
		
		return new org.springframework.security.core.userdetails.User(user.getEmail().toLowerCase(), user.getPassword(),
				grantedAuthorities);
	}
	
	public User save(UserDTO user) throws MessagingException {
		
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		//newUser.setRole(("".equals(user.getRole()) ? Contants.ROLE_CUSTOMER : user.getRole() ));
		newUser.setEmail(user.getEmail().toLowerCase());
		newUser.setFullName(user.getFullName());
		newUser.setPhoneNumber(user.getPhone().strip());
		newUser.setUuid(UUID.randomUUID().toString());
		//sendMailSevice.sendEmail(user.getEmail(), "", user.getFullName(), user.getUsername());
		
		newUser.setStatus(Contants.DE_ACTIVE);
		
		return userDao.save(newUser);
	}

}