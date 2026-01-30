package com.jdevhub.tornado.api.features.notification.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdevhub.tornado.api.features.notification.domain.model.MailMessage;
import com.jdevhub.tornado.api.features.notification.service.MailingService;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/api/*")
@ComponentScan(basePackages = { "com.jdevhub.portal.core.mailing.service" })
public class MailingController {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MailingService mailingService;

	public void setService(MailingService mailingService) {
		this.mailingService = mailingService;
	}
		
	@PostMapping(value = "/send-mail")
	ResponseEntity<Boolean> sendEmail(@RequestBody MailMessage mailMessage)  {
				
		Boolean response=mailingService.sendEmail(mailMessage);
		
        if(response) {
			
			return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
		}else {
			
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
		}
		
	}
}
