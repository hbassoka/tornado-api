package com.jdevhub.intranet.api.features.notification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.jdevhub.intranet.api.features.notification.domain.model.ConfirmationEmailEvent;
import com.jdevhub.intranet.api.features.notification.service.EmailProducerService;

@Service
public class EmailProducerServiceImpl implements EmailProducerService{

	@Autowired
	private KafkaTemplate<String, ConfirmationEmailEvent> kafkaTemplate;

	
	
	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Value("${app.kafka.topic.confirmation-email}")
    private String topic;

		
    public KafkaTemplate<String, ConfirmationEmailEvent> getKafkaTemplate() {
		return kafkaTemplate;
	}

	public String getTopic() {
		return topic;
	}	
	
	@Override
	public void sendConfirmationEmail(ConfirmationEmailEvent event) {
		
		this.kafkaTemplate.send(topic, event);
		
	}
}
