package com.toby.system.dto.vacation;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VacationUpdateDTO {
	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private String reason;
}
