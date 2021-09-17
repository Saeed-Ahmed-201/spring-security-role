package com.security.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.security.enums.eRoles;

@Entity
@Table(name = "roles")
public class Roles {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  
	  @Enumerated(EnumType.STRING)
	  private eRoles name;
	  
	  @Column(columnDefinition = "tinyint(1) default 1")
	  private boolean isActive;
	  
	  @ManyToMany(mappedBy = "roles")
	  private List<User> users;
	  

	public Roles() {
		super();
	}

	public Roles(Long id, eRoles name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public eRoles getName() {
		return name;
	}

	public void setName(eRoles name) {
		this.name = name;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	  
	  
	  
	  
}
