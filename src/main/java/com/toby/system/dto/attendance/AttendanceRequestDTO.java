package com.toby.system.dto.attendance;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AttendanceRequestDTO {
	private String employeeId;
	private String type;
	private LocalDateTime time;
}
