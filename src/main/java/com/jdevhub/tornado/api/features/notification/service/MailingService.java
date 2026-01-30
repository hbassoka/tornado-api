package com.jdevhub.tornado.api.features.notification.service;

import com.jdevhub.tornado.api.features.notification.domain.model.MailMessage;

public interface MailingService extends EmailConsumerService{

	/**
	 * 
	 * @param mailMessage
	 * @return
	 */
	Boolean sendEmail(MailMessage mailMessage);
}
