package com.example.system.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "employee_superior")
public class EmployeeSuperior {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "superior_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
	private Employee superior;
	
	private LocalDateTime createDate;
	
	private LocalDateTime updateDate;
	
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