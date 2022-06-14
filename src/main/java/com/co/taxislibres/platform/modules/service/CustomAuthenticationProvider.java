package com.co.taxislibres.platform.modules.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {


	@Autowired
	private CustomUserDetailImpl jwtUserDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 String username = authentication.getName();
         String password = (String) authentication.getCredentials();
    
           var user = jwtUserDetailsService.loadUserByUsername(username);
    
           if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
               throw new BadCredentialsException("Username not found.");
           }
    
           if (!password.equals(user.getPassword())) {
               throw new BadCredentialsException("Wrong password.");
           }
    
           Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
           log.info("authenticate"+ "ingresó");
           return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
//		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
		log.info("authenticate boolean");

		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));

	}


}
