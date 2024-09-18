package com.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class OutboundCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutboundCrmApplication.class, args);
		System.out.println("Running...");
	}
	
}
