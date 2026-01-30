package com.jdevhub.tornado.api.shared.domain.model;

import java.io.Serializable;

import com.jdevhub.tornado.api.core.security.domain.model.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "motifs")
public class Sujet extends Auditable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String label;
	private String description;
	private boolean active;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Sujet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Sujet(Long id, String code, String label, String description, boolean active) {
		super();
		this.id = id;
		this.code = code;
		this.label = label;
		this.description = description;
		this.active = active;
	}
	
	
	
	

}
