package com.banking.demo;

import com.banking.demo.config.DemoDBStartupRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Bean
	DemoDBStartupRunner configDBRunner() {
		return new DemoDBStartupRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
