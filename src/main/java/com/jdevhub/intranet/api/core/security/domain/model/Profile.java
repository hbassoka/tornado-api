package com.jdevhub.intranet.api.core.security.domain.model;

import java.time.LocalDate;

import org.hibernate.envers.Audited;

import com.jdevhub.intranet.api.shared.domain.model.Titre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "profils")
@Audited
public class Profile extends Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "titre_id")
	private Titre titre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adresse_id")
	private Adresse adresse;

	private String nom;

	private String prenom;

	private String telephone;

	@Column(name = "photo_url")
	private String photoUrl;

	@Column(name = "date_naissance")
	private LocalDate birthdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Titre getTitre() {
		return titre;
	}

	public void setTitre(Titre titre) {
		this.titre = titre;
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

	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Profile() {
		super();
	}

	public Profile(Long id, User user, Titre titre, Adresse adresse, String nom, String prenom, 
			String telephone, String photoUrl, LocalDate birthdate) {
		super();
		this.id = id;
		this.user = user;
		this.titre = titre;
		this.adresse = adresse;
		this.nom = nom;
		this.prenom = prenom;		
		this.telephone = telephone;
		this.photoUrl = photoUrl;
		this.birthdate = birthdate;
	}

}
