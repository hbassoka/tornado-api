package com.jdevhub.intranet.api.features.notification.service;

import com.jdevhub.intranet.api.features.notification.domain.model.MailMessage;

public interface MailingService extends EmailConsumerService{

	/**
	 * 
	 * @param mailMessage
	 * @return
	 */
	Boolean sendEmail(MailMessage mailMessage);
}
