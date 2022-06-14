package com.co.taxislibres.platform.modules.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;

public class ConcurrentSessionFilter implements Filter {

	@Autowired
	private SpringSessionBackedSessionRegistry sessionRegistry;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;

	    HttpSession session = request.getSession(false);
	    
	    if (session != null) {
	    	SessionInformation info = sessionRegistry.getSessionInformation(session
	                .getId());
	    	
	    	if (info.isExpired()) {
	    		info.expireNow();
	    	}
	    }
	    
	    chain.doFilter(request, response);
	}

}
