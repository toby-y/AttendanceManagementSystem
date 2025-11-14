package com.toby.system.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toby.system.entity.Attendance;
import com.toby.system.entity.Employee;
import com.toby.system.repository.EmployeeRepository;
import com.toby.system.service.AttendanceService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/attendance")
@AllArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/{employeeId}")
    public String viewAttendance(@PathVariable String employeeId, Model model) {
        Employee emp = employeeRepository.findById(employeeId).orElseThrow();
        List<Attendance> list = attendanceService.getAttendanceHistory(emp);
        model.addAttribute("employee", emp);
        model.addAttribute("attendances", list);
        return "attendance/attendance_list";
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
