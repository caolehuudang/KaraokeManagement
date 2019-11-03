package com.karaoke.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	
	private final String token;
	
	private String role;
	
	private String fullname;

	public JwtResponse(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}
	

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public JwtResponse(String token, String role) {
		super();
		this.token = token;
		this.role = role;
	}
	
	public JwtResponse(String token, String role, String fullname) {
		super();
		this.token = token;
		this.role = role;
		this.fullname = fullname;
	}
}