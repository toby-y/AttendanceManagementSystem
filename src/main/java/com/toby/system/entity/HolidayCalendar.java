package com.toby.system.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "holiday_calendar")
public class HolidayCalendar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate holidayDate;
	
	@Enumerated(EnumType.STRING)
	private HolidayType holidayType;
	
	private String description;
	
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
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
	
	@PrePersist
	public void prePersist() {
	    createDate = LocalDateTime.now();
	    updateDate = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
	    updateDate = LocalDateTime.now();
	}
	
}
