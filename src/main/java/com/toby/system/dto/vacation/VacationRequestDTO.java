package com.toby.system.dto.vacation;

import java.time.LocalDate;

import com.toby.system.entity.Vacation.VacationType;

import lombok.Data;

@Data
public class VacationRequestDTO {
	private String employeeId;
	private LocalDate startDate;
	private LocalDate endDate;
	private VacationType vacationType;
	private String reason;
}
