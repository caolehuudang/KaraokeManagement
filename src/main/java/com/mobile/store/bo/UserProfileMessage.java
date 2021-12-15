package com.mobile.store.bo;

import com.mobile.store.entity.User;

public class UserProfileMessage {

	String message;
	
	User user;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserProfileMessage(String message, User user) {
		super();
		this.message = message;
		this.user = user;
	}

	public UserProfileMessage() {
		super();
	}
	
}
