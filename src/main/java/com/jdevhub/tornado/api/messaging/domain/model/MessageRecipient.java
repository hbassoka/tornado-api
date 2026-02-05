package com.jdevhub.tornado.api.messaging.domain.model;

import com.jdevhub.tornado.api.security.domain.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "message_recipient")
@Data
public class MessageRecipient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_id", nullable = false)
	private Message message;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipient_id", nullable = false)
	private User recipient;


	@Column(name = "recipient_type", length = 10, nullable = false)
	private String recipientType; // TO, CC, BCC


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Message getMessage() {
		return message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}


	public User getRecipient() {
		return recipient;
	}


	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}


	public String getRecipientType() {
		return recipientType;
	}


	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}


	public MessageRecipient() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MessageRecipient(Long id, Message message, User recipient, String recipientType) {
		super();
		this.id = id;
		this.message = message;
		this.recipient = recipient;
		this.recipientType = recipientType;
	}
	
	
	
}
