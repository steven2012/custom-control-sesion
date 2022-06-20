package com.co.taxislibres.platform.modules.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class CustomUserDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String password;

	private String username;
	
	private Collection<? extends GrantedAuthority> authorities;

	private Boolean enabled;

	private Boolean accountNonExpired;

	private Boolean accountNonLocked;

	private boolean credentialsNonExpired;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
