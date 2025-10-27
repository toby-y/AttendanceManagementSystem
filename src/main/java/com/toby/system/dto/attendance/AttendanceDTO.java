package com.toby.system.dto.attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AttendanceDTO {
	private Long id;
	private String employeeId;
	private LocalDateTime clockIn;
	private LocalDateTime clockOut;
	private LocalDate date;
}
