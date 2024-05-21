package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.feignclient.AddressClient;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.response.AddressResponse;
import com.example.demo.response.EmployeeResponse;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressClient addressClient;

    public EmployeeResponse getEmployeeById(Integer id) {
        Employee employee = employeeRepository.getReferenceById(id);
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

        ResponseEntity<AddressResponse> addressResponseEntity = addressClient.getAddressByEmployeeId(id);
        AddressResponse addressResponse = addressResponseEntity.getBody();

        employeeResponse.setAddressResponse(addressResponse);

        return employeeResponse;
    }
}
