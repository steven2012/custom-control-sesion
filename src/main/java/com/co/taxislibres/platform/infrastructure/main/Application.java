package com.co.taxislibres.platform.infrastructure.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication(scanBasePackages = { "com.co.taxislibres.platform.modules",
		"com.co.taxislibres.platform.infrastructure","com.co.taxislibres.platform.crosscutting"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
