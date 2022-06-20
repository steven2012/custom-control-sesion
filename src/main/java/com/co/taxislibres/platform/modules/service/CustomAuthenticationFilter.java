package com.co.taxislibres.platform.modules.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.session.data.redis.RedisIndexedSessionRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
    public static final String SPRING_SECURITY_FORM_DOMAIN_KEY = "domain";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
        throws AuthenticationException {
    	
    	try {
    		log.info("Authentication"+ "ingresó");

            if (!request.getMethod().equals("POST")) {
                throw new AuthenticationServiceException("Authentication method not supported: " 
                  + request.getMethod());
            }

            CustomAuthenticationToken authRequest = getAuthRequest(request);
            setDetails(request, authRequest);
            var auth=this.getAuthenticationManager().authenticate(authRequest);
//            if( auth.isAuthenticated()) {
//            	String sesion= request.getRequestedSessionId();
//                sessionRedisTemplate.boundHashOps("spring:session:sessions:" + sesion).put("maxInactiveInterval", 60);
//
//           }
            return auth		;
    	}catch(Exception ex) {
    		log.error(ex.getMessage(), ex);
			throw new UsernameNotFoundException("No existe información para las credenciales brindadas");
    	}
        
    }

    private CustomAuthenticationToken getAuthRequest(HttpServletRequest request) {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String domain = obtainDomain(request);
        log.info("getAuthRequest"+ "ingresó");

        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        if (domain == null) {
            domain = "";
        }

        return new CustomAuthenticationToken(username, password, domain);
    }

    private String obtainDomain(HttpServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_DOMAIN_KEY);
    }
}
