package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.response.AddressResponse;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AddressResponse findAddressByEmployeeId(int employeeId) {
		Address address = addressRepository.findAddressByEmployeeId(employeeId);
		
		return modelMapper.map(address, AddressResponse.class);
		
	}
	
}
