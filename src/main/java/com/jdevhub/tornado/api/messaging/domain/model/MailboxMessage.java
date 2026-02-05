package com.jdevhub.tornado.api.messaging.domain.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "mailbox_message")
public class MailboxMessage {

	@EmbeddedId
    private MailboxMessageId id;
	
	@ManyToOne
    @MapsId("mailboxId")
    @JoinColumn(name = "mailbox_id")
	private Mailbox mailbox;


	@ManyToOne
    @MapsId("messageId")
    @JoinColumn(name = "message_id")
	private Message message;


	@Column(name = "is_read", nullable = false)
	private boolean read = false;


	@Column(name = "is_flagged", nullable = false)
	private boolean flagged = false;


	@Column(name = "received_at", nullable = false)
	private Instant receivedAt = Instant.now();


	public MailboxMessageId getId() {
		return id;
	}


	public void setId(MailboxMessageId id) {
		this.id = id;
	}


	public Mailbox getMailbox() {
		return mailbox;
	}


	public void setMailbox(Mailbox mailbox) {
		this.mailbox = mailbox;
	}


	public Message getMessage() {
		return message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}


	public boolean isRead() {
		return read;
	}


	public void setRead(boolean read) {
		this.read = read;
	}


	public boolean isFlagged() {
		return flagged;
	}


	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}


	public Instant getReceivedAt() {
		return receivedAt;
	}


	public void setReceivedAt(Instant receivedAt) {
		this.receivedAt = receivedAt;
	}


	public MailboxMessage() {
		super();
		
	}


	public MailboxMessage(MailboxMessageId id, Mailbox mailbox, Message message, boolean read, boolean flagged,
			Instant receivedAt) {
		super();
		this.id = id;
		this.mailbox = mailbox;
		this.message = message;
		this.read = read;
		this.flagged = flagged;
		this.receivedAt = receivedAt;
	}


	
	
}
