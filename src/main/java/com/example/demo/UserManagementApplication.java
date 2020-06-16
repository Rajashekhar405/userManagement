package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagementApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementApplication.class);
	public static void main(String[] args) {
		LOGGER.info("USER MANAGEMENT service will get bootstrap from here");
		SpringApplication.run(UserManagementApplication.class, args);
	}
}
