package com.toby.system.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.toby.system.common.AuditableEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends AuditableEntity{

    @Id
    private String employeeId;

    private String employeeName;
    private String password;
    private LocalDate birthday;
    private String mail;
    private String role; // USER / ADMIN
    private LocalTime workStart;
    private LocalTime workEnd;
    private boolean enabled = true;
    private boolean locked = false;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Attendance> attendances;
    
    @OneToMany(mappedBy = "superior")
    private List<EmployeeSuperior> subordinates;
}
