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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "preferences")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Audited
public class Preference extends Auditable {

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

    private String language = "fr";
    
    private String theme = "light";
    
    @Column(name="email_notifications")
    private boolean emailNotifications = true;
    
    @Column(name="push_notifications")
    private boolean pushNotifications = true;
    
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public boolean isEmailNotifications() {
		return emailNotifications;
	}
	public void setEmailNotifications(boolean emailNotifications) {
		this.emailNotifications = emailNotifications;
	}
	public boolean isPushNotifications() {
		return pushNotifications;
	}
	public void setPushNotifications(boolean pushNotifications) {
		this.pushNotifications = pushNotifications;
	}
	public Preference() {
		super();
		
	}
	public Preference(Long id, User user, String language, String theme, boolean emailNotifications,
			boolean pushNotifications) {
		super();
		this.id = id;
		this.user = user;
		this.language = language;
		this.theme = theme;
		this.emailNotifications = emailNotifications;
		this.pushNotifications = pushNotifications;
	}
	
   
	
	
	
}
