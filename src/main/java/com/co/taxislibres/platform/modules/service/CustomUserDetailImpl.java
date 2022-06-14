package com.co.taxislibres.platform.modules.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.co.taxislibres.platform.crosscutting.persistence.repository.DriverRepository;

@Component
public class CustomUserDetailImpl implements CustomUserDetailsService  {

 
	@Autowired
	private DriverRepository driverRepository;
	/**
	 * This method verify credential to access Api wiht token, before consumer
	 * endpoints
	 */
	@Override
	public UserDetails loadUserByUsername(String idUsuario) throws UsernameNotFoundException {
		
		var user=driverRepository.findById(idUsuario);
		
		if(!user.isPresent()) {
			return null;
		}	
		
		var userEntity= user.get();
		return new User(userEntity.getId(), new BCryptPasswordEncoder().encode(userEntity.getEmail()), true, true, true, true, getAuthorities());
	}

	
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        List<GrantedAuthority> list = new ArrayList<>();

	        list.add(new SimpleGrantedAuthority("ADMIN"));
	        return list;
	    }


}
