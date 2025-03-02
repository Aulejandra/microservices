package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.EmployeeResponse;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // http://localhost:8080/employee-app/api/employee/{id}
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") Integer id) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);

        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

    // http://localhost:8080/employee-app/api/employee
    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeResponse>> findAllEmployees() {
        List<EmployeeResponse> employeeResponses = employeeService.findAllEmployees();

        return ResponseEntity.status(HttpStatus.OK).body(employeeResponses);
    }
}
