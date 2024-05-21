package com.example.demo.openfeignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.response.AddressResponse;

@FeignClient(name = "address-service", path = "/address-app/api/")
public interface AddressClient {

    @GetMapping("/address/{employeeId}")
    ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") Integer employeeId);

    @GetMapping("/address")
    public ResponseEntity<List<AddressResponse>> findAllAddress();
}
