package com.example.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.response.AddressResponse;

//http://localhost:8082/address-app/api/address/1
@FeignClient(name = "address--service", url = "http://localhost:8082", path = "/address-app/api")
public interface AddressClient {

    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int employeeId);

}
