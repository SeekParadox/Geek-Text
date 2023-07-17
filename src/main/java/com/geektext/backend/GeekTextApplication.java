package com.geektext.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
		basePackages = "com",
		excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org\\.springframework\\.security\\..*")
)
public class GeekTextApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeekTextApplication.class, args);
	}

}
