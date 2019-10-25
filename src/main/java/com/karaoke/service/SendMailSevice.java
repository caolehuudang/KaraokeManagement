package com.karaoke.service;

import javax.mail.MessagingException;

public interface SendMailSevice {

	void sendEmail(String receiver, String title, String content);
	
	void sendEmail(String receiver, String title, String content, String username) throws MessagingException;
	
}
