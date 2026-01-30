package com.jdevhub.tornado.api.features.notification.service;

import com.jdevhub.tornado.api.features.notification.domain.model.ConfirmationEmailEvent;

public interface EmailProducerService {

	void sendConfirmationEmail(ConfirmationEmailEvent event);
}
