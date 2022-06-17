package com.co.taxislibres.platform.modules.api.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController

@RequestMapping("${server.servlet.context-path}/sesion")
@Api(tags = "Caracteristicas", description = "API de caracteristicas")
public class SesionController {

    @Autowired()
    FindByIndexNameSessionRepository<? extends ExpiringSession> sessionRepository;

    @RequestMapping("/")
   public  String listSessions(Principal principal, HttpSession session, Model model) {
        Collection<? extends ExpiringSession> userSessions = sessionRepository.findByIndexNameAndIndexValue(PRINCIPAL_NAME_INDEX_NAME, principal.getName()).values();
        model.addAttribute("sessions", userSessions);
        // this gives us the correct session ID, as the HttpSession is backed by Spring Session:
        model.addAttribute("currSessionId", session.getId());
        return "index";
    }	
	
	
//	@GetMapping("/")
//	public String home(Model model, HttpSession session) {
//		@SuppressWarnings("unchecked")
//		List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
//
//		if (messages == null) {
//			messages = new ArrayList<>();
//		}
//		model.addAttribute("sessions", messages);
//		model.addAttribute("currSessionId", session.getId());
//
//		return "index";
//	}
	   
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@PostMapping(value = "/login-person")
//	public  @ResponseBody String authentication(@RequestParam("login") String userName,
//	        @RequestParam("password") String password, HttpServletRequest request) {
//
//	    Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
//	            userName, password);
//	    try {
//
//	        Authentication authentication = authenticationManager
//	                .authenticate(authenticationToken);
//
//
//	        SecurityContext securityContext = SecurityContextHolder
//	                .getContext();
//
//	        securityContext.setAuthentication(authentication);
//
//	        HttpSession session = request.getSession(true);
//	        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
//
//	        return "sucess";
//	    } catch (AuthenticationException ex) {
//	        return "fail " + ex.getMessage();
//	    }
//	}
//	    
//	 @RequestMapping(value="/helloworld", method = RequestMethod.GET)
//	    public String helloWorld(ModelMap model) {
//	    model.addAttribute("message", "Welcome to the Hello World page");
//	    return "helloworld";
//	     
//	    }

	
}
