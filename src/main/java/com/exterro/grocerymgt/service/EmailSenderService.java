package com.exterro.grocerymgt.service;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/*
Author name : vimalraj.vijayakumar
Creation Date : 27-Jun-2024
*/

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String toEmail, String subject, String body) {
		MimeMessage message1 = mailSender.createMimeMessage();
		String path = "C:\\eclipse\\grocerymgt\\Invoice.pdf";
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message1, true);
			helper.setFrom("vimalexterro@gmail.com");
			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setText(body);

			FileSystemResource file = new FileSystemResource(new File(path));
			helper.addAttachment("Invoice.pdf", file);
			mailSender.send(message1);
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
	}
}