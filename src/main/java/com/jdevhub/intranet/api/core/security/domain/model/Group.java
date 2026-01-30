package com.jdevhub.intranet.api.core.security.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups")
public class Group  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	private String name;   // e.g. "Admin Group", "User Group"
	
	private String label;   // e.g. "Admin Group", "User Group"
		

	private boolean deletable = true;
		    
	/***
	 * // Roles assigned to the group
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "group_roles", 
	joinColumns = @JoinColumn(name = "group_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles=new HashSet<>();  
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	public Group() {
		super();
		
	}

	public Group(Long id, String name,String label,boolean deletable, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.label=label;
		this.deletable=deletable;		
		this.roles = roles;
		
	}

	

}
