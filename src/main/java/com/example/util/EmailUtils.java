package com.example.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to, String sub, String body) throws MessagingException {
		MimeMessage mimeMsg = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);
		
		helper.setTo(to);
		
		helper.setSubject(sub);
		
		helper.setText(body, true);
		
		mailSender.send(mimeMsg);
	}
}
