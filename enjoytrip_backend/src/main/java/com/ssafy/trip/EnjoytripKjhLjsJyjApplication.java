package com.ssafy.trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = { "com.ssafy.trip" })
@SpringBootApplication
public class EnjoytripKjhLjsJyjApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnjoytripKjhLjsJyjApplication.class, args);
	}

}