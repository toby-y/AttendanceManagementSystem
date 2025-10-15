package com.example.system.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.system.entity.Attendance;
import com.example.system.entity.Employee;
import com.example.system.repository.EmployeeRepository;
import com.example.system.service.AttendanceService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final EmployeeRepository employeeRepository;

    public AttendanceController(AttendanceService attendanceService, EmployeeRepository employeeRepository) {
        this.attendanceService = attendanceService;
        this.employeeRepository = employeeRepository;
    }

    // 社員を選んで打刻する簡易サンプル
    @GetMapping("/{employeeId}")
    public String viewAttendance(@PathVariable String employeeId, Model model) {
        Employee emp = employeeRepository.findById(employeeId).orElseThrow();
        List<Attendance> list = attendanceService.getAttendanceHistory(emp);
        model.addAttribute("employee", emp);
        model.addAttribute("attendances", list);
        return "attendance_list";
    }

    @PostMapping("/{employeeId}/clockin")
    public String clockIn(@PathVariable String employeeId) {
        Employee emp = employeeRepository.findById(employeeId).orElseThrow();
        attendanceService.clockIn(emp);
        return "redirect:/attendance/" + employeeId;
    }

    @PostMapping("/{employeeId}/clockout")
    public String clockOut(@PathVariable String employeeId) {
        Employee emp = employeeRepository.findById(employeeId).orElseThrow();
        attendanceService.clockOut(emp);
        return "redirect:/attendance/" + employeeId;
    }
}
