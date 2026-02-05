package com.jdevhub.tornado.api.security.domain.model;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "used_tokens")
public class UsedToken implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private String jti;
    private Instant expiry;
	public String getJti() {
		return jti;
	}
	public void setJti(String jti) {
		this.jti = jti;
	}
	public Instant getExpiry() {
		return expiry;
	}
	public void setExpiry(Instant expiry) {
		this.expiry = expiry;
	}
	public UsedToken() {
		super();
		
	}
	public UsedToken(String jti, Instant expiry) {
		super();
		this.jti = jti;
		this.expiry = expiry;
	}
    
}
