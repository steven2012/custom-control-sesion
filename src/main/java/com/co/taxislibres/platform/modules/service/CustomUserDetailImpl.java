package com.co.taxislibres.platform.modules.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.co.taxislibres.platform.crosscutting.persistence.repository.DriverRepository;

@Service
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
		return new UserSesion(userEntity.getId(), userEntity.getEmail(), true, true, true, true, new ArrayList<>());
//		return new User(userEntity.getId(), userEntity.getEmail(), new ArrayList<>());
	
	}


}
