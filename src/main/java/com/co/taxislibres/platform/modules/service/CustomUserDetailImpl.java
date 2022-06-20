package com.co.taxislibres.platform.modules.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.co.taxislibres.platform.crosscutting.persistence.repository.DriverRepository;


@Service("userDetailsService")
public class CustomUserDetailImpl implements CustomUserDetailsService  {

 
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private RedisOperations<Object, Object> sessionRedisTemplate;

	
	/**
	 * This method verify credential to access Api wiht token, before consumer
	 * endpoints
	 */
	@Override
	public UserDetails loadUserByUsername(String idUsuario) throws UsernameNotFoundException {
		
		var user=driverRepository.findByEmail(idUsuario);
		
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("No existe información para las credenciales brindadas");
		}	
	
//      sessionRedisTemplate.boundHashOps("spring:session:sessions:" + "pilas papi").put("maxInactiveInterval", 60);
		var userEntity= user.get();
//		return new CustomUserDetail(userEntity.getEmail(), new BCryptPasswordEncoder().encode(userEntity.getId()), getAuthorities(), true, true, true, true);
		return new User(userEntity.getEmail(), new BCryptPasswordEncoder().encode(userEntity.getId()), true, true, true, true, getAuthorities());
	}

	
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        List<GrantedAuthority> list = new ArrayList<>();

	        list.add(new SimpleGrantedAuthority("ADMIN"));
	        return list;
	    }


}
