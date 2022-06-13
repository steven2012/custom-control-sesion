package com.co.taxislibres.platform.modules.api.controller;

//import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
//import org.springframework.session.ExpiringSession;
//import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController

@RequestMapping("${server.servlet.context-path}/sesion")
@Api(tags = "Caracteristicas", description = "API de caracteristicas")
@Slf4j
public class SesionController {

//    @Autowired
//    FindByIndexNameSessionRepository<? extends ExpiringSession> sessions;
//
//    @RequestMapping("/")
//   public  String listSessions(Principal principal, HttpSession session, Model model) {
//        Collection<? extends ExpiringSession> userSessions = sessions.findByIndexNameAndIndexValue(PRINCIPAL_NAME_INDEX_NAME, principal.getName()).values();
//        model.addAttribute("sessions", userSessions);
//        // this gives us the correct session ID, as the HttpSession is backed by Spring Session:
//        model.addAttribute("currSessionId", session.getId());
//        return "index";
//    }	
	
    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        getDomain().ifPresent(d -> {
            model.addAttribute("domain", d);
        });
        return "index";
    }

    @RequestMapping("/user/index")
    public String userIndex(Model model) {
        getDomain().ifPresent(d -> {
            model.addAttribute("domain", d);
        });
        return "user/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    private Optional<String> getDomain() {
        Authentication auth = SecurityContextHolder.getContext()
            .getAuthentication(); 
        String domain = null;
        if (auth != null && !auth.getClass().equals(AnonymousAuthenticationToken.class)) {
            User user = (User) auth.getPrincipal();
        }
        return Optional.ofNullable(domain);
    }
	
}
