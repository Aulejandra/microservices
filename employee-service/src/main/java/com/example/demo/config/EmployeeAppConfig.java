package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeAppConfig {

	@Value("${address.service.base.url}")
	private String addressBaseUrl;

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	WebClient webClient() {
		return WebClient.builder().baseUrl(addressBaseUrl).build();
	}

}
