package com.co.taxislibres.platform.modules.api.controller;

//import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;


//import org.springframework.session.ExpiringSession;
//import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController

@RequestMapping("${server.servlet.context-path}/sesion")
@Api(tags = "Caracteristicas", description = "API de caracteristicas")
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
	
	 @RequestMapping(value="/helloworld", method = RequestMethod.GET)
	    public String helloWorld(ModelMap model) {
	    model.addAttribute("message", "Welcome to the Hello World page");
	    return "helloworld";
	     
	    }
	   
//	    @RequestMapping(value="/secured/home", method = RequestMethod.GET)
//	    public String securedHome(ModelMap model) {
//	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	        User user=null;
//	        if (principal instanceof User) {
//	        user = ((User)principal);
//	        }
//	     
//	    String name = user.getUsername();
//	    model.addAttribute("username", name);
//	    model.addAttribute("message", "Welcome to the secured page");
//	    return "home";
//	     
//	    }
	
}
