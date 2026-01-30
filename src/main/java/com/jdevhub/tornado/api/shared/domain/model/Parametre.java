package com.jdevhub.tornado.api.shared.domain.model;

import java.io.Serializable;

import com.jdevhub.tornado.api.core.security.domain.model.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parametres")
public class Parametre extends Auditable implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
    @Column(name = "prop_key", nullable = false,unique = true, updatable = false,insertable = false)
    private String nom;

    @Column(name = "prop_value", nullable = false)
    private String valeur;

    private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Parametre() {
		super();
		
	}

	public Parametre(Long id, String nom, String valeur, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.valeur = valeur;
		this.description = description;
	}

	

	
	


}
