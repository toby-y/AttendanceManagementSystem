package com.example.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.system.entity.Employee;
import com.example.system.entity.Vacation;
import com.example.system.form.VacationRequestForm;
import com.example.system.repository.EmployeeRepository;
import com.example.system.service.VacationService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/vacation")
@AllArgsConstructor
public class VacationController {

	private final VacationService vacationService;
	private final EmployeeRepository employeeRepository;
	
	@GetMapping("/employee/{employeeId}")
	public String vacationList(@PathVariable String employeeId,Model model) {
		Employee emp = employeeRepository.findById(employeeId).orElseThrow();
		model.addAttribute("employeeId",employeeId);
		model.addAttribute("employeeName", emp.getEmployeeName());
		model.addAttribute("vacations", vacationService.employeeVacationList(employeeId));
		return "vacation/vacation_list"; 
	}
	
	@GetMapping("/request")
	public String vacationRequestForm(Model model) {
		model.addAttribute("vacationRequestForm", new VacationRequestForm());
		return "vacation/vacation_request"; // ← 画面表示用（GET）
	}
	
	@PostMapping("/request/submit")
	public String submitVacationRequest(@ModelAttribute VacationRequestForm form,Model model) {
		
		// Form → Entity に変換
        Vacation vacation = new Vacation();
        vacation.setStartDate(form.getStartDate());
        vacation.setEndDate(form.getEndDate());
        vacation.setVacationType(form.getVacationType());
        vacation.setReason(form.getReason());
        vacation.setEmployee(employeeRepository.findById("E001").orElseThrow()); // 仮: ログイン中社員を想定

        // Service経由で登録
        vacationService.vacationRequest(vacation);

        // 完了後に一覧画面へリダイレクト
        return "redirect:/vacation/employee/" + vacation.getEmployee().getEmployeeId();
	}
}
