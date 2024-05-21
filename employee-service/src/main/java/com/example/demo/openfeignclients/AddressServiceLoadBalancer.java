package com.example.demo.openfeignclients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

import feign.Feign;

@LoadBalancerClient(name = "address-service")
public class AddressServiceLoadBalancer {

    @LoadBalanced
    @Bean
    Feign.Builder feiBuilder() {
        return Feign.builder();
    }
}
