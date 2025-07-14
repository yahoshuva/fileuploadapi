package com.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	
	//plain text
	// html emails -> design
	// from email,to email, subject,body
	
	@Autowired
	public JavaMailSender javaMailSender; //package of mail in pom.xml
	
//	public void sendPlainEmail(String fromEmail,String toEmail,String subject,String mailBody) {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(fromEmail);
//		message.setTo(toEmail);
//		message.setSubject(subject);
//		message.setText(mailBody);
//		
//		System.out.println("Sending email from: " + fromEmail + ", configured username: " + javaMailSender);
//
//		javaMailSender.send(message);
//
//		
//		
//		
//		
//	}
	
	public void sendPlainEmail(String fromEmail, String toEmail, String subject, String mailBody) {
		try {
			System.out.println(">>> Preparing email <<<");

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(fromEmail);
			message.setTo(toEmail);
			message.setSubject(subject);
			message.setText(mailBody);

			System.out.println(">>> Sending email from: " + fromEmail);

			javaMailSender.send(message);

			System.out.println(">>> Email sent successfully <<<");
		} catch (Exception e) {
			System.out.println(">>> Failed to send email <<<");
			e.printStackTrace(); // This is important
		}
	}


}
