package com.financial;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class FinancialAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialAppApplication.class, args);
		System.out.println("Financial Application is start running...!");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
