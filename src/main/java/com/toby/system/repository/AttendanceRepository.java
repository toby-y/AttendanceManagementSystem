package com.toby.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toby.system.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance,Long>{
	List<Attendance> findByEmployeeEmployeeIdOrderByClockInDesc(String employeeId);
}
