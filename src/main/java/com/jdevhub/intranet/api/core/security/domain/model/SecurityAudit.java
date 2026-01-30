package com.jdevhub.intranet.api.core.security.domain.model;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "security_audit")
public class SecurityAudit implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Long id;

    private String username;
    private String event;
    private String ip;
    private Instant timestamp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	public SecurityAudit() {
		super();
		
	}
	public SecurityAudit(Long id, String username, String event, String ip, Instant timestamp) {
		super();
		this.id = id;
		this.username = username;
		this.event = event;
		this.ip = ip;
		this.timestamp = timestamp;
	}
    
    
}
