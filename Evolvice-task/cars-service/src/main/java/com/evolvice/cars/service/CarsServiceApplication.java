package com.evolvice.cars.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CarsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsServiceApplication.class, args);
	}
}
