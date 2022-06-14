package com.co.taxislibres.platform.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.co.taxislibres.platform.modules.service.CustomAuthenticationProvider;
import com.co.taxislibres.platform.modules.service.CustomUserDetailImpl;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailImpl jwtUserDetailsService;
	
//	@Autowired
//	private SpringSessionBackedSessionRegistry sessionRegistry;
//	
//	@Autowired
//    private FindByIndexNameSessionRepository<ExpiringSession> sessionRepository;
	   
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
   

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
//	@Bean
//	public AuthenticationProvider authenticationProvider(CustomUserDetailsService jwtUserDetailsService) {
//		log.info("authenticationProvider "+"ingresor");
//		return new CustomUserDetailsAuthenticationProvider(passwordEncoder(), jwtUserDetailsService);
//	}
//	

//	public CustomAuthenticationFilter authenticationFilter() throws Exception {
//		CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
//		filter.setAuthenticationManager(authenticationManagerBean());
//		filter.setAuthenticationFailureHandler(failureHandler());
//		log.info("authenticationFilter "+"ingresor");
//
//		return filter;
//	}
//
//	public SimpleUrlAuthenticationFailureHandler failureHandler() {
//		return new SimpleUrlAuthenticationFailureHandler("/login?error=true");
//	}

	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(jwtUserDetailsService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		log.info("AuthenticationManagerBuilder "+"ingresor");
//		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(authenticationProvider());
    }


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		log.info("configure "+"ingresor");

    	http.
//    	addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class).	
    	formLogin().and()
            .logout().and().
            csrf().disable()
//            .sessionManagement()
//                .maximumSessions(1)
//                    .sessionRegistry(sessionRegistry())
//                    // set to true to prevent logins after reaching max sessions:
//                    .maxSessionsPreventsLogin(false)
//                    .and()
//                .and()
            .authorizeRequests()
                .antMatchers("/").authenticated();
        //@formatter:on
	}
	
//	@Bean
//	public SpringSessionBackedSessionRegistry sessionRegistry() {
//		return new SpringSessionBackedSessionRegistry(this.sessionRepository);
//	}
}
