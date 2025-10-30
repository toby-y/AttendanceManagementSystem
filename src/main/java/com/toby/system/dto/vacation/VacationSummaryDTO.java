package com.toby.system.dto.vacation;

import lombok.Data;

@Data
public class VacationSummaryDTO {
	private String employeeId;
	private String employeeName;
	private int paidLeaveTotal;
	private int paidLeaveUsed;
	private int paidLeaveRemaining;
}
