package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeAppConfig {

    // No lo usamos, es a modo de ejemplo
    // @Value("${address.service.base.url}")
    // private String addressBaseUrl;

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
