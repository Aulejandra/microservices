package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.entity.Employee;
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
	private WebClient webClient;

	public EmployeeResponse getEmployeeById(Integer id) {
		Employee employee = employeeRepository.getReferenceById(id);
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

		AddressResponse addressResponse = webClient.get().uri("/address/" + id).retrieve()
				.bodyToMono(AddressResponse.class).block();

		employeeResponse.setAddressResponse(addressResponse);

		return employeeResponse;
	}
}
