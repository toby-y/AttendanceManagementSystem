package com.toby.system.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Vacation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate startDate;
	private LocalDate endDate;

	@Enumerated(EnumType.STRING)
	private VacationType vacationType;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	private String reason;
	
	private LocalDateTime createDate;
	private LocalDateTime updateDate;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "decided_by")
	private Employee decidedBy;
	private String rejectedReason;
	private LocalDateTime decidedDate;

	
	public enum VacationType {
	    PAID("有給"),
	    ABSENTEEISM("欠勤"),
	    SPECIAL("特別休暇"),
	    HOLIDAY("祝日");

	    private final String label;

	    VacationType(String label) {
	        this.label = label;
	    }

	    public String getLabel() {
	        return label;
	    }
	}

	public enum Status {
	    APPLYING("申請中"),
	    APPROVED("承認済み"),
	    REJECTED("棄却");

	    private final String label;

	    Status(String label) {
	        this.label = label;
	    }

	    public String getLabel() {
	        return label;
	    }
	}

	
	@PrePersist
	public void prePersist() {
	    if (status == null) {
	        status = Status.APPLYING;
	    }
	    createDate = LocalDateTime.now();
	    updateDate = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
	    updateDate = LocalDateTime.now();
	}

}
