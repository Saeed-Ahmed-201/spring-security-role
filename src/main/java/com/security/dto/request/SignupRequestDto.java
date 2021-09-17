package com.security.dto.request;

import java.util.Set;


public class SignupRequestDto {
	
	  private String userName;
	  private String email;
	  private String password;
	  private Set<String> roles;
	
	  
	  public SignupRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SignupRequestDto(String userName, String email, String password, Set<String> roles) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
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


	public Set<String> getRoles() {
		return roles;
	}


	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}



}
