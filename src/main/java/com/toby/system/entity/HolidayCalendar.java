package com.toby.system.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.toby.system.common.AuditableEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "holiday_calendar")
public class HolidayCalendar extends AuditableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate holidayDate;
	
	@Enumerated(EnumType.STRING)
	private HolidayType holidayType;
	
	private String description;
	
	public enum HolidayType {
		NATIONAL("国民の祝日"),
		COMPANY("社休");
		
		private final String label;

		HolidayType(String label) {
	        this.label = label;
	    }

	    public String getLabel() {
	        return label;
	    }
	}
}
