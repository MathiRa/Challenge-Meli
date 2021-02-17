package com.meli.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ChApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChApplication.class, args);
	}

}
