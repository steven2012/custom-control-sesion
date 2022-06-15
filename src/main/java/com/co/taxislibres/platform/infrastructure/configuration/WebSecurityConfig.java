package com.co.taxislibres.platform.infrastructure.configuration;

import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.co.taxislibres.platform.modules.service.CustomUserDetailImpl;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailImpl jwtUserDetailsService;
//	
//    @Autowired 
//    private SpringSessionBackedSessionRegistry sessionRegistry;

//	 @Autowired
//     FindByIndexNameSessionRepository<ExpiringSession> sessionRepository;
	 
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

//	  @Bean
//	    public SpringSessionBackedSessionRegistry sessionRegistry() {
//	        return new SpringSessionBackedSessionRegistry(this.sessionRepository);
//	    }
//	
	
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
            .sessionManagement()
                .maximumSessions(1)
//                    .sessionRegistry(sessionRegistry())
//                    // set to true to prevent logins after reaching max sessions:
                    .maxSessionsPreventsLogin(true)
                    .and()
                .and()
            .authorizeRequests()
                .antMatchers("/").authenticated();
        //@formatter:on
	}
	

}
