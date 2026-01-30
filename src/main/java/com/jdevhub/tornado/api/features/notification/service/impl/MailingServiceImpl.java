package com.jdevhub.tornado.api.features.notification.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jdevhub.tornado.api.features.notification.domain.model.ConfirmationEmailEvent;
import com.jdevhub.tornado.api.features.notification.domain.model.MailMessage;
import com.jdevhub.tornado.api.features.notification.service.MailingService;

@Service
public class MailingServiceImpl implements MailingService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender mailSender;

	@Value("${app.mail.from}")
	private String fromAddress;

	@Value("${app.mail.from}")
	private String toAdress;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public Boolean sendEmail(MailMessage mailMessage) {

		Boolean ret = true;

		try {

			SimpleMailMessage mailRequest = new SimpleMailMessage();
			mailRequest.setFrom(mailMessage.getFrom());
			mailRequest.setTo(this.toAdress);
			mailRequest.setSubject(mailMessage.getSubject());
			mailRequest.setText(mailMessage.getBody());
			mailSender.send(mailRequest);
			ret = true;
		} catch (Exception e) {

			logger.error(e.getMessage());
			ret = true;
		}

		return ret;
	}

	@Override
	public void listen(ConfirmationEmailEvent event) {
		
		String txt= """ 
				
				Envoie de la confirmation d'envoie de mail
				
				
				""";
		logger.info(txt);
	}

}
