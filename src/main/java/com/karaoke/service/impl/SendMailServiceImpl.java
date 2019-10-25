package com.karaoke.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.karaoke.common.Contants;
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
        javaMailSender.send(msg);
	}

	@Override
	public void sendEmail(String receiver, String title, String fullName, String username) throws MessagingException {
		
		MimeMessage msg = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
		helper.setTo(receiver);
		
		helper.setSubject("Confirm create accout");
		
		helper.setText("<h1>Dear "+ fullName +",</h1>"
						+"<h2>Welcome to Karaoke Lala</h2>"
						+ "<span>Thank you for using our service, </span>"
						+ "<span>"
						+ "<a href=\""+Contants.URL_SERVER+"/confirm-account?username="+ username +"\">"
						+ " Click here for active account, Please! "
						+ "</a>"
						+ "</span>", true);
        javaMailSender.send(msg);
		
	}

}
