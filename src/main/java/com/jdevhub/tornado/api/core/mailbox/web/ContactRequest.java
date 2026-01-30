package com.jdevhub.tornado.api.core.mailbox.web;

public class ContactRequest {

	private Long titreId;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private Long sujetId;
	private String message;
	public Long getTitreId() {
		return titreId;
	}
	public void setTitreId(Long titreId) {
		this.titreId = titreId;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Long getSujetId() {
		return sujetId;
	}
	public void setSujetId(Long sujetId) {
		this.sujetId = sujetId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ContactRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContactRequest(Long titreId, String nom, String prenom, String email, String telephone, Long sujetId,
			String message) {
		super();
		this.titreId = titreId;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.sujetId = sujetId;
		this.message = message;
	}

	
	

}
