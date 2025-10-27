package com.toby.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.toby.system.entity.Vacation;

public interface VacationRepository extends JpaRepository<Vacation,Long>{
	
	List<Vacation> findByEmployee_EmployeeId(String employeeId);
	
	@Query("""
		    SELECT v
		    FROM Vacation v
		    JOIN EmployeeSuperior es ON v.employee.employeeId = es.employee.employeeId
		    WHERE es.superior.employeeId = :superiorId
		      AND v.status = 'APPLYING'
			""")
	List<Vacation> findPendingApprovalsBySuperiorId(@Param("superiorId") String superiorId);

}
