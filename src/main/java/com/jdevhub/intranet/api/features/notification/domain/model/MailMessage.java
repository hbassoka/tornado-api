package com.jdevhub.intranet.api.features.notification.domain.model;

import java.io.Serializable;

public class MailMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String names;
	private String to;
	private String from;
	private String subject;
	private String body;
	
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public MailMessage() {
		super();
		
	}
	public MailMessage(String names, String to, String from, String subject, String body) {
		super();
		this.names = names;
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.body = body;
	}
	

	
}
