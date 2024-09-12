package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@Configuration
@SpringBootApplication

public class BitCoinApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(BitCoinApplication.class, args);
	}
	
	
}
