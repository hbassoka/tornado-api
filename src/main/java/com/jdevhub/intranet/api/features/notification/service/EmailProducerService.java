package com.jdevhub.intranet.api.features.notification.service;

import com.jdevhub.intranet.api.features.notification.domain.model.ConfirmationEmailEvent;

public interface EmailProducerService {

	void sendConfirmationEmail(ConfirmationEmailEvent event);
}
