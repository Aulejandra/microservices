package com.example.demo.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.AddressResponse;
import com.example.demo.service.AddressService;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    // http://localhost:${ramdon}/address-app/api/address/${employeeId}
    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int employeeId) {
        Logger.getAnonymousLogger().info("AddressService - " + employeeId);
        AddressResponse addressResponse = addressService.findAddressByEmployeeId(employeeId);

        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);

    }

    // http://localhost:${ramdon}/address-app/api/address
    @GetMapping("/address")
    public ResponseEntity<List<AddressResponse>> findAllAddress() {
        List<AddressResponse> addressResponses = addressService.findAllAddress();

        return ResponseEntity.status(HttpStatus.OK).body(addressResponses);
    }

}
