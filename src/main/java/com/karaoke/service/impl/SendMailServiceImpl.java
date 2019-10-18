package com.karaoke.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.karaoke.service.SendMailSevice;

@Service
public class SendMailServiceImpl implements SendMailSevice{

	@Autowired
	JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(String receiver, String title, String content) {
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("caolehuudangga@gmail.com");

        msg.setSubject("Hello My Friend");
        msg.setText("My name is Dang");
        System.out.println("sending..................");
        javaMailSender.send(msg);
	}

	@Override
	public void sendEmail(String receiver, String title, String content, String username) {
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(receiver);

        msg.setSubject("Confirm create accout");
        
        msg.setText("To confirm your account, please click here : "
        		  +"http://localhost:9998/confirm-account?username=" + username );
        		  
        System.out.println("sending..................");
        
        javaMailSender.send(msg);
		
	}

}
