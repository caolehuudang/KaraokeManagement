package com.karaoke.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.karaoke.bo.UserDTO;
import com.karaoke.common.Contants;
import com.karaoke.config.JwtTokenUtil;
import com.karaoke.dao.UserDao;
import com.karaoke.model.JwtRequest;
import com.karaoke.model.JwtResponse;
import com.karaoke.model.User;
import com.karaoke.service.impl.JwtUserDetailsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	UserDao userDao;

	
	@CrossOrigin
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword(),
				userDetails.getAuthorities());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		User user = userDao.findByUsername(authenticationRequest.getUsername());

		return ResponseEntity.ok(new JwtResponse(token, 
				userDetails.getAuthorities().iterator().next().toString(),
				user.getFullName(), user.getId()));
	}
	
	@CrossOrigin
	@PostMapping("/loginViaToken")
	public UserDTO loginViaToken(@RequestHeader(value = "Authorization") String authorization) {
		
		 UserDetails userDetails = userDetailsService
					.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(authorization.substring(7)));
		 
		UserDTO userDto = new UserDTO();
		User user = userDao.findByUsername(userDetails.getUsername());
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setFullName(user.getFullName());
		userDto.setPhone(user.getPhone());
		userDto.setRole(userDetails.getAuthorities().iterator().next().toString());
		
		return userDto;
	}
	
	@PostMapping("/findUserByToken")
	public UserDTO findUserByToken(@RequestHeader(value = "Authorization") String authorization) {
		
		 UserDetails userDetails = userDetailsService
					.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(authorization.substring(7)));
		 
		UserDTO userDto = new UserDTO();
		User user = userDao.findByUsername(userDetails.getUsername());
		userDto.setUsername(user.getUsername());
		userDto.setFullName(user.getFullName());
		userDto.setPhone(user.getPhone());
		userDto.setEmail(user.getEmail());
		userDto.setRole(userDetails.getAuthorities().iterator().next().toString());
		userDto.setId(user.getId());
		userDto.setImage(user.getImage());
		userDto.setVip(user.getVip());
		
		return userDto;
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		
		User userExist = userDao.findByUsername(user.getUsername().strip());
		
		User phoneExist = userDao.findUserByPhone(user.getPhone().strip());
		
		if(userExist != null) {
			return ResponseEntity.ok(Contants.USER_EXISTED);
		}else if(phoneExist != null){
			return ResponseEntity.ok("PHONE_EXIST");
		}else {
			return ResponseEntity.ok(userDetailsService.save(user));
		}
		
	}

	private void authenticate(String username, String password, Collection<? extends GrantedAuthority> collection) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, collection));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}