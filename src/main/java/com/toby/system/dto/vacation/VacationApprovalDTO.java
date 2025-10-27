package com.toby.system.dto.vacation;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VacationApprovalDTO {
	private String decidedBy;
	private LocalDate decidedDate;
}
