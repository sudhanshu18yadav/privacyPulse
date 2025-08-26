package com.privacyPulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrivacyPulseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrivacyPulseApplication.class, args);

		var f1 = new XmlEngine("src/main/resources/finders_default.xml");
		System.out.println("privacy pulse started working");
	}

}
