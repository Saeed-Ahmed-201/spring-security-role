package com.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.security.enums.eRoles;
import com.security.model.Roles;
import com.security.model.User;

public class UserDetailsImpl implements UserDetails{
	
	private long id;
	private String fullName;
	private String email;
	@JsonIgnore
	private String password;
	private boolean isEnabled;

	private Collection<? extends GrantedAuthority> authorties;

	public UserDetailsImpl(long id, String fullName, String email, String password, boolean isEnabled,
			Collection<? extends GrantedAuthority> authorties) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.isEnabled = isEnabled;
		this.authorties = authorties;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorties;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}
	
	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Roles role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
		}
//		user.getRoles().stream().forEach(role -> 
//		                       authorities.add(new SimpleGrantedAuthority(role.getName().name()))
//		                );
		return new UserDetailsImpl(user.getId(), user.getFullName(), user.getEmail(), user.getPassword(), user.isEnabled(), authorities);
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
