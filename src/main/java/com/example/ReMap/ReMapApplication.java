package com.example.ReMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ReMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReMapApplication.class, args);
	}

}
