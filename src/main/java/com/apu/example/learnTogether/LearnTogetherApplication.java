package com.apu.example.learnTogether;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LearnTogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnTogetherApplication.class, args);
	}

}
