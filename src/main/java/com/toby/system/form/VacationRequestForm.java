package com.toby.system.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.toby.system.entity.Vacation.VacationType;

import lombok.Data;

@Data
public class VacationRequestForm {

	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate endDate;
	
	@NotNull
	private VacationType vacationType;
	
	@NotBlank
	private String reason;
}
