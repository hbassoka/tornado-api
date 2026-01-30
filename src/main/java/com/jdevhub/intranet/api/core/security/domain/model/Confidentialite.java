package com.jdevhub.intranet.api.core.security.domain.model;

import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "confidentialites")
@Audited
public class Confidentialite extends Auditable {

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

	@Column(name="profile_visibility")
	private String profileVisibility = "public"; // public, friends, private
	
	@Column(name="show_email")
	private boolean showEmail = false;
	
	@Column(name="show_telephone")
	private boolean showTelephone = true;
	
	@Column(name="show_birthdate")
	private boolean showBirthdate = false;
	
	
	@Column(name="data_processing_consent")
	private boolean dataProcessingConsent = false;
	
	
	@Column(name="marketing_consent")
	private boolean marketingConsent = false;
	
	@Column(name="third_party_sharing")
	private boolean thirdPartySharing = false;

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

	public String getProfileVisibility() {
		return profileVisibility;
	}

	public void setProfileVisibility(String profileVisibility) {
		this.profileVisibility = profileVisibility;
	}

	public boolean isShowEmail() {
		return showEmail;
	}

	public void setShowEmail(boolean showEmail) {
		this.showEmail = showEmail;
	}

	public boolean isShowTelephone() {
		return showTelephone;
	}

	public void setShowTelephone(boolean showTelephone) {
		this.showTelephone = showTelephone;
	}

	public boolean isShowBirthdate() {
		return showBirthdate;
	}

	public void setShowBirthdate(boolean showBirthdate) {
		this.showBirthdate = showBirthdate;
	}

	public boolean isDataProcessingConsent() {
		return dataProcessingConsent;
	}

	public void setDataProcessingConsent(boolean dataProcessingConsent) {
		this.dataProcessingConsent = dataProcessingConsent;
	}

	public boolean isMarketingConsent() {
		return marketingConsent;
	}

	public void setMarketingConsent(boolean marketingConsent) {
		this.marketingConsent = marketingConsent;
	}

	public boolean isThirdPartySharing() {
		return thirdPartySharing;
	}

	public void setThirdPartySharing(boolean thirdPartySharing) {
		this.thirdPartySharing = thirdPartySharing;
	}

	
	
	public Confidentialite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Confidentialite(Long id, User user, String profileVisibility, boolean showEmail, boolean showTelephone,
			boolean showBirthdate, boolean dataProcessingConsent, boolean marketingConsent, boolean thirdPartySharing) {
		super();
		this.id = id;
		this.user = user;
		this.profileVisibility = profileVisibility;
		this.showEmail = showEmail;
		this.showTelephone = showTelephone;
		this.showBirthdate = showBirthdate;
		this.dataProcessingConsent = dataProcessingConsent;
		this.marketingConsent = marketingConsent;
		this.thirdPartySharing = thirdPartySharing;
	}
	


}
