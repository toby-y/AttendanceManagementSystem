package com.example.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.system.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,String>{

}
