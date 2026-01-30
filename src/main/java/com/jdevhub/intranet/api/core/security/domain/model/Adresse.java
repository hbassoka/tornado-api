package com.jdevhub.intranet.api.core.security.domain.model;

import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Audited
@Entity
@Table(name = "adresses")
public class Adresse extends Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private String ligne1;
	
	private String ligne2;
	
	private String ligne3;
	
	@Column(name="code_postal")
	private String codePostal;
	
	private String ville;
	
	private String region;
	
	private String pays;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLigne1() {
		return ligne1;
	}

	public void setLigne1(String ligne1) {
		this.ligne1 = ligne1;
	}

	public String getLigne2() {
		return ligne2;
	}

	public void setLigne2(String ligne2) {
		this.ligne2 = ligne2;
	}

	public String getLigne3() {
		return ligne3;
	}

	public void setLigne3(String ligne3) {
		this.ligne3 = ligne3;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	
	

	public Adresse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Adresse(Long id, String ligne1, String ligne2, String ligne3, String codePostal,
			String ville, String region, String pays) {
		super();
		this.id = id;
		
		this.ligne1 = ligne1;
		this.ligne2 = ligne2;
		this.ligne3 = ligne3;
		this.codePostal = codePostal;
		this.ville = ville;
		this.region = region;
		this.pays = pays;
	}

	
	
	
	
	
	

}
