package com.toby.system.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.toby.system.dto.vacation.VacationDTO;
import com.toby.system.dto.vacation.VacationRequestDTO;
import com.toby.system.dto.vacation.VacationSummaryDTO;
import com.toby.system.entity.Employee;
import com.toby.system.entity.Vacation;
import com.toby.system.repository.EmployeeRepository;
import com.toby.system.repository.VacationRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class VacationService{

	private final VacationRepository vacationRepository;
	private final EmployeeRepository employeeRepository;
	
	public List<VacationDTO> employeeVacationList(String employeeId){
		List<Vacation> vacationList = vacationRepository.findByEmployee_EmployeeId(employeeId);
		List<VacationDTO> vacationDTOList = new ArrayList<>();
		for(Vacation vacation : vacationList) {
			VacationDTO vacationDTO = new VacationDTO();
			vacationDTO.setEmployeeId(employeeId);
			vacationDTO.setEmployeeName(vacation.getEmployee().getEmployeeName());
			vacationDTO.setStartDate(vacation.getStartDate());
			vacationDTO.setEndDate(vacation.getEndDate());
			vacationDTO.setVacationType(vacation.getVacationType().getLabel());
			vacationDTO.setReason(vacation.getReason());
			vacationDTO.setStatus(vacation.getStatus().getLabel());
			if(vacation.getDecidedBy() != null) {
				vacationDTO.setDecidedBy(vacation.getDecidedBy().getEmployeeName());
			} else {
				vacationDTO.setDecidedBy("");
			}
			vacationDTO.setDecidedDate(vacation.getDecidedDate());
			vacationDTO.setRejectedReason(vacation.getRejectedReason());
			vacationDTOList.add(vacationDTO);
		}
		return vacationDTOList;
	}
	
	public void vacationRequest(Vacation vacation) {
		validateVacation(vacation);
		vacationRepository.save(vacation);
	}
	
	public VacationDTO vacationRequest(VacationRequestDTO dto) {
		log.info("休暇申請受信:{}",dto);
		
		// DTOをEntityに変換
		Vacation vacation = new Vacation();
		vacation.setStartDate(dto.getStartDate());
		vacation.setEndDate(dto.getEndDate());
		vacation.setVacationType(dto.getVacationType());
		vacation.setReason(dto.getReason());
		
		Employee employee = employeeRepository.findById(dto.getEmployeeId())
				.orElseThrow(() -> new IllegalArgumentException("社員が見つかりません"));
		
		vacation.setEmployee(employee);
		
		validateVacation(vacation);
		
	    Vacation saved = vacationRepository.save(vacation);

	    // 保存結果をDTOに変換して返却
	    VacationDTO vacationDTO = new VacationDTO();
	    vacationDTO.setStartDate(saved.getStartDate());
	    vacationDTO.setEndDate(saved.getEndDate());
	    vacationDTO.setVacationType(saved.getVacationType().getLabel());
	    vacationDTO.setReason(saved.getReason());
	    vacationDTO.setStatus(saved.getStatus().getLabel());
	    return vacationDTO;
	}
	
	public VacationDTO approvalVacation(Long id,String decidedBy) {

		Vacation approvalVacation = vacationRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("休暇が見つかりません"));
		
		Employee decidedEmployee = employeeRepository.findById(decidedBy)
				.orElseThrow(() -> new IllegalArgumentException("社員が見つかりません"));
		
		approvalVacation.setDecidedBy(decidedEmployee);
		approvalVacation.setStatus(Vacation.Status.APPROVED);
		approvalVacation.setDecidedDate(LocalDateTime.now());
		
		Vacation saved = vacationRepository.save(approvalVacation);
		
		return VacationDTO.fromEntity(saved);
	}
	
public VacationDTO rejectionVacation(Long id,String decidedBy,String reason) {
		
		Vacation approvalVacation = vacationRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("休暇が見つかりません"));
		
		Employee decidedEmployee = employeeRepository.findById(decidedBy)
				.orElseThrow(() -> new IllegalArgumentException("社員が見つかりません"));
		
		approvalVacation.setDecidedBy(decidedEmployee);
		approvalVacation.setStatus(Vacation.Status.REJECTED);
		approvalVacation.setRejectedReason(reason);
		approvalVacation.setDecidedDate(LocalDateTime.now());
		
		Vacation saved = vacationRepository.save(approvalVacation);
		
		return VacationDTO.fromEntity(saved);
	}

	public VacationDTO getVacationById(Long id) {
	    return vacationRepository.findById(id)
	        .map(VacationDTO::fromEntity)
	        .orElse(null);
	}

	
	public VacationSummaryDTO getVacationSummary(String employeeId) { 
		List<VacationDTO> vacations = employeeVacationList(employeeId);
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new IllegalArgumentException("社員が見つかりません"));
		int years = Period.between(employee.getHireDate(),LocalDate.now()).getYears();
		int total = 10 + years; // 有給総数
		
		int used = (int) vacations.stream()
				.filter(v -> "有給".equals(v.getVacationType()))
				.filter(v -> "承認済み".equals(v.getStatus()))
				.mapToLong(v -> ChronoUnit.DAYS.between(v.getStartDate(),v.getEndDate()) + 1)
				.sum();
		
		VacationSummaryDTO dto = new VacationSummaryDTO();
		dto.setEmployeeId(employeeId);
		dto.setEmployeeName(employee.getEmployeeName());
		dto.setPaidLeaveTotal(total);
		dto.setPaidLeaveUsed(used);
		dto.setPaidLeaveRemaining(total - used);
		return dto;
	}
	
	private void validateVacation(Vacation vacation) {
		LocalDate startDate = vacation.getStartDate();
		LocalDate endDate = vacation.getEndDate();
		
		if (startDate == null || endDate == null) {
		    throw new IllegalArgumentException("申請失敗:日付が未設定です");
		}
		
		if (vacation.getEmployee() == null || vacation.getEmployee().getEmployeeId() == null) {
		    log.info("社員IDが取得できませんでした");
		    throw new IllegalArgumentException("申請失敗:不明な社員ID");
		}
		String employeeId = vacation.getEmployee().getEmployeeId();

		// 開始日と終了日が逆転していないか
		if(startDate.isAfter(endDate)) {
			log.info("開始日{}と{}終了日が逆になっています",startDate,endDate);
			throw new IllegalArgumentException("申請失敗:開始日、終了日が逆順");
		}
		// 過去の申請と重複した日付ではないか
		List<Vacation> vacations = vacationRepository.findByEmployee_EmployeeId(employeeId);
		for(Vacation v : vacations) {
			boolean overlap = !v.getEndDate().isBefore(startDate) && !v.getStartDate().isAfter(endDate);
		    if (overlap) {
		        log.info("社員ID {} の申請 {}～{} が過去申請 {}～{} と重複しています", 
		                 employeeId, startDate, endDate, v.getStartDate(), v.getEndDate());
		        throw new IllegalArgumentException("申請失敗:日付重複");
		    }
		}
	}
}
