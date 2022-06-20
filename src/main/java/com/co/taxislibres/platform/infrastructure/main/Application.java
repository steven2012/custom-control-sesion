package com.co.taxislibres.platform.infrastructure.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
//import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
//@SpringBootApplication(scanBasePackages = { "com.co.taxislibres.platform.modules",
//		"com.co.taxislibres.platform.infrastructure","com.co.taxislibres.platform.crosscutting"})
//@EnableSpringHttpSession
//@SpringBootApplication(scanBasePackages= {"com.co.taxislibres.platform.infrastructure"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	@Bean
//	public HttpSessionEventPublisher httpSessionEventPublisher() {
//	    return new HttpSessionEventPublisher();
//	}
}
