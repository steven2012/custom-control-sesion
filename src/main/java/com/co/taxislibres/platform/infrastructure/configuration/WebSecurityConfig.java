package com.co.taxislibres.platform.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.co.taxislibres.platform.modules.service.CustomAuthenticationFilter;
import com.co.taxislibres.platform.modules.service.CustomUserDetailsAuthenticationProvider;
import com.co.taxislibres.platform.modules.service.CustomUserDetailsService;
//import com.co.taxislibres.platform.modules.service.CustomUserDetail;
//import com.co.taxislibres.platform.modules.service.SpringSessionBackedSessionRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailImpl")
	private CustomUserDetailsService jwtUserDetailsService;

//	@Autowired
//	private SpringSessionBackedSessionRegistry sessionRegistry;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(CustomUserDetailsService jwtUserDetailsService) {
		return new CustomUserDetailsAuthenticationProvider(passwordEncoder(), jwtUserDetailsService);
	}

	public CustomAuthenticationFilter authenticationFilter() throws Exception {
		CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationFailureHandler(failureHandler());
		return filter;
	}

	public SimpleUrlAuthenticationFailureHandler failureHandler() {
		return new SimpleUrlAuthenticationFailureHandler("/login?error=true");
	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider(jwtUserDetailsService));
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
    	http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
            .antMatchers("/css/**", "/index").permitAll()
            .antMatchers("/user/**").authenticated()
        .and()
        .formLogin().loginPage("/login")
        .and()
        .logout()
        .logoutUrl("/logout");    
    	
    	   	
//    	http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class).
//    	formLogin().and()
//            .logout().and()
//            .csrf().disable()
//            .sessionManagement()
//                .maximumSessions(1)
//                    .sessionRegistry(sessionRegistry)
//                    // set to true to prevent logins after reaching max sessions:
//                    .maxSessionsPreventsLogin(false)
//                    .and()
//                .and()
//            .authorizeRequests()
//                .antMatchers("/").authenticated();
        //@formatter:on
	}
}
