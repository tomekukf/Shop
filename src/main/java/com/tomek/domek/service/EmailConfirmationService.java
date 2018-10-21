package com.tomek.domek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailConfirmationService {

	private JavaMailSender mailSender;

	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	private final String subject = "koniec";
	private final String text = "how Are you ? ";

	public void sendEmailConfirmation(String email) throws MailException {

		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(email);
		smm.setSubject(subject);
		smm.setText(text);
		mailSender.send(smm);

		// try {
		//// mailSender.send(smm);
		// } catch (Exception e) {
		//
		// }

	}
	
	
	
	
}
