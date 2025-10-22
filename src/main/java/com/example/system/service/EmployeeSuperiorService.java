package com.example.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.system.entity.EmployeeSuperior;
import com.example.system.repository.EmployeeSuperiorRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeSuperiorService {
	
	private final EmployeeSuperiorRepository employeeSupriorRepository;
	
	public Map<String,String> getSuperiorList(String employeeId){
		
		List<EmployeeSuperior> supList = employeeSupriorRepository.findByEmployee_EmployeeId(employeeId);
		Map<String,String> supIdByNameList = new HashMap<>();
		for(EmployeeSuperior sup : supList) {
			supIdByNameList.put(sup.getSuperior().getEmployeeId(), sup.getSuperior().getEmployeeName());
		}
		return supIdByNameList;
	}
}
