package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.openfeignclients.AddressClient;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.response.AddressResponse;
import com.example.demo.response.EmployeeResponse;

@Service
public class EmployeeService {

    private static final Logger log = Logger.getAnonymousLogger();

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

    public List<EmployeeResponse> findAllEmployees() {
        // All Employees
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = Arrays
                .asList(modelMapper.map(employeeList, EmployeeResponse[].class));

        List<AddressResponse> addressList = getAllAddresses();

        employeeResponses.parallelStream().forEach(employeeResponse -> setAddressList(employeeResponse, addressList));

        return employeeResponses;
    }

    private List<AddressResponse> getAllAddresses() {
        try {
            // All Addresses
            ResponseEntity<List<AddressResponse>> addressResponseEntity = addressClient.findAllAddress();
            List<AddressResponse> addressList = addressResponseEntity.getBody();
            return addressList;
        } catch (Exception e) {
            log.severe(() -> e.getMessage());
            return new ArrayList<AddressResponse>(0);
        }
    }

    private void setAddressList(EmployeeResponse employeeResponse, List<AddressResponse> addressList) {
        employeeResponse.setAddressResponse(addressList.parallelStream()
                .filter(address -> address.getEmployeeId() == employeeResponse.getId()).findAny().orElse(null));
    }

}
