package com.gmail.kulacholeg.canon.connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableScheduling
@EnableRetry
@EnableWebMvc
public class CanonIr2204NUsageListApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanonIr2204NUsageListApplication.class, args);
	}

}
