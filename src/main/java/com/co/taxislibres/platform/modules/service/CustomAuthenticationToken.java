package com.co.taxislibres.platform.modules.service;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String domain;

	    public CustomAuthenticationToken(Object principal, Object credentials, String domain) {
	        super(principal, credentials);
	        this.domain = domain;
	        super.setAuthenticated(false);
	    }

	    public CustomAuthenticationToken(Object principal, Object credentials, String domain, 
	        Collection<? extends GrantedAuthority> authorities) {
	        super(principal, credentials, authorities);
	        this.domain = domain;
	        super.setAuthenticated(true); // must use super, as we override
	    }

	    public String getDomain() {
	        return this.domain;
	    }

}
