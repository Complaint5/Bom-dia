package com.complaint5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication @EnableScheduling
public class BomDiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BomDiaApplication.class, args);
	}

}
