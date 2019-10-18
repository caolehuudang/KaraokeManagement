package com.karaoke.service;

public interface SendMailSevice {

	void sendEmail(String receiver, String title, String content);
	
	void sendEmail(String receiver, String title, String content, String username);
	
}
