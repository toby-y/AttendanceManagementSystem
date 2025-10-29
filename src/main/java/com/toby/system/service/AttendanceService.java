package com.toby.system.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.toby.system.entity.Attendance;
import com.toby.system.entity.Employee;
import com.toby.system.repository.AttendanceRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    // 出勤打刻
    public Attendance clockIn(Employee employee) {
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setClockIn(LocalDateTime.now());
        attendance.setCreateDate(LocalDateTime.now());
        attendance.setUpdateDate(LocalDateTime.now());
        return attendanceRepository.save(attendance);
    }

    // 退勤打刻
    public Attendance clockOut(Employee employee) {
        // 最新の出勤レコードを取得
        List<Attendance> list = attendanceRepository.findByEmployeeEmployeeIdOrderByClockInDesc(employee.getEmployeeId());
        if (list.isEmpty()) return null;

        Attendance latest = list.get(0);
        if (latest.getClockOut() != null) return null; // すでに退勤済み

        latest.setClockOut(LocalDateTime.now());
        latest.setUpdateDate(LocalDateTime.now());
        return attendanceRepository.save(latest);
    }

    public List<Attendance> getAttendanceHistory(Employee employee) {
        return attendanceRepository.findByEmployeeEmployeeIdOrderByClockInDesc(employee.getEmployeeId());
    }
}
