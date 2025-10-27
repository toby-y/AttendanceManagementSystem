package com.toby.system.dto.vacation;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VacationDTO {
	private LocalDate startDate;
	private LocalDate endDate;
	private String vacationType;
	private String reason;
	private String status;
	private String decidedBy;
	private LocalDateTime decidedDate;
	private String rejectedReason;
}
