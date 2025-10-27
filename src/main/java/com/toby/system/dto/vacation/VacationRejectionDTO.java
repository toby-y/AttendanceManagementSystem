package com.toby.system.dto.vacation;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VacationRejectionDTO {
	private String decidedBy;
	private String rejectedReason;
	private LocalDate decidedDate;
}
