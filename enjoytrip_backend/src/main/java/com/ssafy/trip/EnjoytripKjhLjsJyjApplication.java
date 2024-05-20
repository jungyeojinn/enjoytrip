package com.ssafy.trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.ssafy.trip" })
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EnjoytripKjhLjsJyjApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnjoytripKjhLjsJyjApplication.class, args);
	}

}