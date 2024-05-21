package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeAppConfig {

    @Value("${address.service.base.url}")
    private String addressBaseUrl;

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
