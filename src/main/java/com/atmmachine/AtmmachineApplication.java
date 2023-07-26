package com.atmmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.atmmachine")

public class AtmmachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmmachineApplication.class, args);
		
	}

}
