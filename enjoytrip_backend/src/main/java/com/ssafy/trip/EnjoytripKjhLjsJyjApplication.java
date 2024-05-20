package com.ssafy.trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.ssafy.trip.jwt.config.JwtProperties;

@EnableWebMvc
@ComponentScan(basePackages = { "com.ssafy.trip" })
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ConfigurationPropertiesScan(basePackageClasses = {JwtProperties.class})
public class EnjoytripKjhLjsJyjApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnjoytripKjhLjsJyjApplication.class, args);
	}

}