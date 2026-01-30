package com.jdevhub.tornado.api.core.security.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jdevhub.tornado.api.core.mailbox.domain.model.Mailbox;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Audited
public class User extends Auditable implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String provider;

	private String providerId;
	
	private String username;
	
	private String email;

	@Column(name = "password_hash")
	private String password;

	@Column(name = "password_raw")
	private String  rawPassword;
	
	private String secret;

	private boolean enabled = true;
	
	private boolean deletable = true;

	@Column(name = "two_factor_enabled")
	private boolean twoFactorEnabled = false;

	@Column(name = "account_non_expired")
	private boolean accountNonExpired = true;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked = true;

	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired = true;

	@NotAudited
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_groups", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	private Set<Group> groups=new HashSet<>();  
	
	
	@NotAudited
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Profile profile;
	
	@NotAudited
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Preference preferences;

   // @NotAudited
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Confidentialite confidentialite;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Mailbox mailbox;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public boolean isTwoFactorEnabled() {
		return twoFactorEnabled;
	}

	public void setTwoFactorEnabled(boolean twoFactorEnabled) {
		this.twoFactorEnabled = twoFactorEnabled;
	}
	
		
	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getRawPassword() {
		return rawPassword;
	}

	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
	public User() {
		super();

	}
	

	public User(Long id, String provider, String providerId, String username,String email,
			String password, String rawPassword, String secret, boolean enabled,boolean deletable, boolean twoFactorEnabled,
			boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, Set<Group> groups
			) {
		super();
		this.id = id;
		this.provider = provider;
		this.providerId = providerId;		
		this.username = username;
		this.email = email;
		this.password = password;
		this.rawPassword = rawPassword;
		this.secret = secret;
		this.enabled = enabled;
		this.deletable = deletable;
		this.twoFactorEnabled = twoFactorEnabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.groups = groups;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Role> allRoles =this.getAllRoles();
		Set<Permission> allPermissions =this.getAllPermissions();
		
		List<GrantedAuthority> autorities=new ArrayList<>();	
		
		autorities.addAll(allRoles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).distinct().collect(Collectors.toList()));
		
		autorities.addAll(allPermissions.stream().map(permission-> new SimpleGrantedAuthority(permission.getName())).distinct().collect(Collectors.toList()));
				
		return autorities;
	}

	
	private  Set<Role> getAllRoles() {
		
		
	    Set<Role> allRoles = new HashSet<>(); // Start with the roles assigned directly to the user

	    for (Group group : this.getGroups()) {
	        allRoles.addAll(group.getRoles()); // Add roles from the groups the user belongs to
	    }
	    return allRoles;
	}
	
	private  Set<Permission> getAllPermissions() {
			
		
	    Set<Permission> allPermissions = new HashSet<>(); // Start with the permissions assigned directly to the user

	    // Include permissions from roles associated with the user
	    for (Role role : getAllRoles()) {
	        allPermissions.addAll(role.getPermissions()); // Add permissions granted by the roles
	    }
	    return allPermissions;
	}  
	

    
}
