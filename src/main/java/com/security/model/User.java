package com.security.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;
	   
	   private String fullName;
	  
	   @Column(unique= true)
	   private String email;
	   
	   private String password;
	   
	   @Column(columnDefinition = "tinyint(1) default 0")
	   private boolean isEnabled = false;
	   
	   @ManyToMany(fetch = FetchType.EAGER)
	   @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name ="user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
	   private Set<Roles> roles;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String fullName, String email, String password, boolean isEnabled, Set<Roles> roles) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.isEnabled = isEnabled;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}


}
