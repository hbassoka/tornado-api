package com.jdevhub.intranet.api.core.mailbox.domain.dto;

import java.util.List;

import com.jdevhub.intranet.api.core.security.domain.dto.UserDto;


public class SendMessageRequest {

	
	private UserDto sender;
    private List<UserDto> recipients;
    private String body;
    private String priority;
	public UserDto getSender() {
		return sender;
	}
	public void setSender(UserDto sender) {
		this.sender = sender;
	}
	public List<UserDto> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<UserDto> recipients) {
		this.recipients = recipients;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public SendMessageRequest() {
		super();
		
	}
    
	public SendMessageRequest(UserDto sender, List<UserDto> recipients, String body, String priority) {
		super();
		this.sender = sender;
		this.recipients = recipients;
		this.body = body;
		this.priority = priority;
	}
	
	
    
    

}
