package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM Address WHERE employee_Id=:employeeId")
    Address findAddressByEmployeeId(@Param("employeeId") int employeeId);

}
