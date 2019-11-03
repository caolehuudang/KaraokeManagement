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
				user.getFullName()));
	}
	
	@CrossOrigin
	@PostMapping("/loginViaToken")
	public UserDTO loginViaToken(@RequestHeader(value = "Authorization") String authorization) {
		
		 UserDetails userDetails = userDetailsService
					.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(authorization.substring(7)));
		 
		UserDTO userDto = new UserDTO();
		User user = userDao.findByUsername(userDetails.getUsername());
		userDto.setUsername(user.getUsername());
		userDto.setFullName(user.getFullName());
		userDto.setRole(userDetails.getAuthorities().iterator().next().toString());
		
		return userDto;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
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