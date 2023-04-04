package com.gabrielbazante.lab_dgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LabDgsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabDgsApplication.class, args);
	}

}
