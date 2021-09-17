package com.security.dto.response;

import java.util.Set;

public class LoginSuccessfulResponseDto {
	
	   private String token;
	   private String userFullName;
	   private String userEmail;
	   private Set<String> roles;
	
	   public LoginSuccessfulResponseDto(String token, String userFullName, String userEmail, Set<String> roles) {
			super();
			this.token = token;
			this.userFullName = userFullName;
			this.userEmail = userEmail;
			this.roles = roles;
	   }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	   
}
