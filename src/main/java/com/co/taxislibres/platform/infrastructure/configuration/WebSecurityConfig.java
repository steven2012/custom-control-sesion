package com.co.taxislibres.platform.infrastructure.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;

import com.co.taxislibres.platform.modules.service.SpringSessionBackedSessionRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SpringSessionBackedSessionRegistry sessionRegistry;
	
	
	 @Autowired
     FindByIndexNameSessionRepository<ExpiringSession> sessionRepository;
	 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
        http.formLogin().and()
            .logout().and()
            .csrf().disable()
            .sessionManagement()
                .maximumSessions(1)
                    .sessionRegistry(sessionRegistry())
                    // set to true to prevent logins after reaching max sessions:
                    .maxSessionsPreventsLogin(false)
                    .and()
                .and()
            .authorizeRequests()
                .antMatchers("/").authenticated();
        //@formatter:on
	}

	@Bean
	public static ServletListenerRegistrationBean httpSessionEventPublisher() {
	    return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
	}	
	 @Bean
    public  SpringSessionBackedSessionRegistry sessionRegistry() {
             return new SpringSessionBackedSessionRegistry(this.sessionRepository);
     }
}
