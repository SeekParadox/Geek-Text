package com.geektext.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GeekTextApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeekTextApplication.class, args);
	}

}
