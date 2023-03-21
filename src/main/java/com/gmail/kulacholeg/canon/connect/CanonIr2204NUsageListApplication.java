package com.gmail.kulacholeg.canon.connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CanonIr2204NUsageListApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanonIr2204NUsageListApplication.class, args);
	}

}
