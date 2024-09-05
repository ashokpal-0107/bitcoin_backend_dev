package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//@ComponentScan(basePackages = "com.example.demo.*")
@Configuration
@SpringBootApplication
//@EnableSwagger2
public class BitCoinApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(BitCoinApplication.class, args);
	}
	
	/*
	 * @Bean public Docket productApi() { return new
	 * Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.basePackage("com.example.demo")).build(); }
	 */

}
