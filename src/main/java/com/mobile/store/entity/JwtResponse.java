package com.mobile.store.entity;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	
	private Long id;
	
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
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JwtResponse(String token, String role) {
		super();
		this.token = token;
		this.role = role;
	}
	
	public JwtResponse(String token, String role, String fullname, Long id) {
		super();
		this.id = id;
		this.token = token;
		this.role = role;
		this.fullname = fullname;
	}
}