package com.privacyPulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

@SpringBootApplication
public class PrivacyPulseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrivacyPulseApplication.class, args);

		XmlEngine engine = new XmlEngine("src/main/resources/finders_default.xml");
		List<String> result = engine.find("Here is my id : chlorine-finder@testchlorine.com and my machine inf o:  124.234.223.12 , ok ?");
		System.out.println("privacy pulse started working: "+ result);
	}

}
