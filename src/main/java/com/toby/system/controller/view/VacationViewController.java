package com.toby.system.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toby.system.form.VacationRequestForm;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/vacation")
@AllArgsConstructor
@Slf4j
public class VacationViewController {

    @GetMapping("/list")
    public String showVacationList() {
        return "vacation/vacation_list"; // 一覧画面
    }

    @GetMapping("/request")
    public String showVacationRequestForm(Model model) {
        model.addAttribute("vacationRequestForm", new VacationRequestForm());
        return "vacation/vacation_request"; // 申請画面
    }
    
    @GetMapping("/detail/{id}")
    public String showVacationDetail(@PathVariable Long id,Model model) {
    	
    	model.addAttribute("vacationId",id);
    	model.addAttribute("loginUserRole","USER"); // TODO:仮置き
    	
    	return "vacation/vacation_detail";
    }
}
