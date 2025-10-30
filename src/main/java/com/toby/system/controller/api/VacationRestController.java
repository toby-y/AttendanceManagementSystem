package com.toby.system.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toby.system.dto.vacation.VacationDTO;
import com.toby.system.dto.vacation.VacationRequestDTO;
import com.toby.system.dto.vacation.VacationSummaryDTO;
import com.toby.system.service.VacationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/vacation")
@AllArgsConstructor
@Slf4j
public class VacationRestController {

	private final VacationService vacationService;
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<List<VacationDTO>> getEmployeeVacations(@PathVariable String employeeId){
		List<VacationDTO> vacations = vacationService.employeeVacationList(employeeId);
		return ResponseEntity.ok(vacations);
	}
	
	@GetMapping("/summary/{employeeId}")
	public ResponseEntity<VacationSummaryDTO> getVacationSummary(@PathVariable String employeeId){
		VacationSummaryDTO vacationSummary = vacationService.getVacationSummary(employeeId);
		return ResponseEntity.ok(vacationSummary);
	}
	
	@PostMapping("/request")
	public ResponseEntity<VacationDTO> submitVacationRequest(@RequestBody VacationRequestDTO dto){
		VacationDTO savedVacation = vacationService.vacationRequest(dto);
		return ResponseEntity.ok(savedVacation);
		
	}
}
