package com.co.taxislibres.platform.modules.api.controller;

import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    FindByIndexNameSessionRepository<? extends ExpiringSession> sessions;

    @RequestMapping("/")
   public  String listSessions(Principal principal, HttpSession session, Model model) {
        Collection<? extends ExpiringSession> userSessions = sessions.findByIndexNameAndIndexValue(PRINCIPAL_NAME_INDEX_NAME, principal.getName()).values();
        model.addAttribute("sessions", userSessions);
        // this gives us the correct session ID, as the HttpSession is backed by Spring Session:
        model.addAttribute("currSessionId", session.getId());
        return "index";
    }

	
}
