package com.toby.system.dto.vacation;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VacationRequestDTO {
	private LocalDate startDate;
	private LocalDate endDate;
	private String vacationType;
	private String reason;
}
