package com.example.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.system.entity.Vacation;

public interface VacationRepository extends JpaRepository<Vacation,Long>{
	
	List<Vacation> findByEmployee_EmployeeId(String employeeId);
}
