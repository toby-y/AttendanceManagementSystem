package com.toby.system.dto.vacation;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.toby.system.entity.Vacation;

import lombok.Data;

@Data
public class VacationDTO {
	private Long id;
	private String employeeId;
	private String employeeName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String vacationType;
	private String reason;
	private String status;
	private String decidedBy;
	private LocalDateTime decidedDate;
	private String rejectedReason;
	
	public static VacationDTO fromEntity(Vacation entity) {
        VacationDTO dto = new VacationDTO();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getEmployeeId());
        dto.setEmployeeName(entity.getEmployee().getEmployeeName());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setVacationType(entity.getVacationType().name());
        dto.setReason(entity.getReason());
        dto.setStatus(entity.getStatus().name());
        dto.setDecidedBy(
                entity.getDecidedBy() != null ? entity.getDecidedBy().getEmployeeName() : null
            );

        dto.setDecidedDate(
            entity.getDecidedDate() != null ? entity.getDecidedDate() : null
        );
        dto.setDecidedDate(entity.getDecidedDate());
        dto.setRejectedReason(entity.getRejectedReason());
        
        return dto;
	}
}
