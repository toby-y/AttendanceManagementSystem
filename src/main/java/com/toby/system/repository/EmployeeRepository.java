package com.toby.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toby.system.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,String>{

}
