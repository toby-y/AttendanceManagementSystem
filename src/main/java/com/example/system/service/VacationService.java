package com.example.system.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.system.entity.Vacation;
import com.example.system.repository.VacationRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class VacationService{

	private final VacationRepository vacationRepository;
	
	public List<Vacation> employeeVacationList(String employeeId){
		return vacationRepository.findByEmployee_EmployeeId(employeeId);
	}
	
	public void vacationRequest(Vacation vacation) {
		
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
		vacationRepository.save(vacation);
	}

}
