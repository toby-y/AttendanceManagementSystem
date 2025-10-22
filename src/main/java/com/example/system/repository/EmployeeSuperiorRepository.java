package com.example.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.system.entity.EmployeeSuperior;

public interface EmployeeSuperiorRepository extends JpaRepository<EmployeeSuperior,Long>{
	
	// 特定の社員の上司一覧を取得
    List<EmployeeSuperior> findByEmployee_EmployeeId(String employeeId);
    
    // 特定の上司の部下一覧を取得
    List<EmployeeSuperior> findBySuperior_EmployeeId(String superiorId);
}