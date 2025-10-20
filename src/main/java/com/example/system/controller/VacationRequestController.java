package com.example.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.system.form.VacationRequestForm;

@Controller
@RequestMapping("/vacation")
public class VacationRequestController {
	
	@PostMapping("/vacationRequest")
	public String vacationRequestForm(@ModelAttribute VacationRequestForm form) {
		return "employee/vacationRequest"; // ← 画面表示用（GET）
	}
	
	@GetMapping("/vacationRequest")
	public String submitVacationRequest(@ModelAttribute VacationRequestForm form,Model model) {
		
		
		
		return "emploee/vacationRequest";
	}
}
