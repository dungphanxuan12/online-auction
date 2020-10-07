package com.auction.utils.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.auction.utils.IEmailService;

@Component
public class MailService implements IEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.host.gmail}")
	private String serverMail;

	@Override
	public Boolean sendMail(String to, String subject, String text) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(serverMail);
			mailMessage.setTo(to);
			mailMessage.setSubject(subject);
			mailMessage.setText(text);
			mailSender.send(mailMessage);
		} catch (MailException e) {
			return false;
		}
		return true;
	}

}
