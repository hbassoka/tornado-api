package com.jdevhub.intranet.api.features.notification.service;

import org.springframework.kafka.annotation.KafkaListener;

import com.jdevhub.intranet.api.features.notification.domain.model.ConfirmationEmailEvent;

public interface EmailConsumerService {

	@KafkaListener(topics = "${app.kafka.topic.confirmation-email}", groupId = "email-group")
	void listen(ConfirmationEmailEvent event) ;
}
