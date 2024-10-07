package com.crm.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.model.InboundReport;
import com.crm.repository.InboundAddReportRepository;

@Service
public class InboundAddReportService {
	
	@Autowired
	private InboundAddReportRepository inboundRepo;
	
	// Saving Inbound report data
	public InboundReport saveInboundReport(InboundReport report) {
		report.setDate(LocalDate.now());
		return inboundRepo.save(report);
	}
	
	// finding inbound report by mobile number
	public InboundReport findReportByMobile(String mobile) {
		return inboundRepo.findByMobile(mobile);
	}
	
	// finding caller details by caller id 
	public List<InboundReport> findCalledData(long id) {
		return inboundRepo.findByUserIdAndDate(id, LocalDate.now());
	}
	
	// count total report by id and current date
	public Long countReportByIdAndDate(Long id) {
		return inboundRepo.countByIdAndDate(id, LocalDate.now());
	}
	
	// count total report by id 
	public Long countReportById(Long id) {
		return inboundRepo.countByUserId(id);
	}
	
	public Long countCallConnected(Long id, String connectionType, LocalDate date) {
		return inboundRepo.countByUserIdAndConnectionTypeAndDateAndAssignTime(id, connectionType, date);
	}
	
	public List<InboundReport> findReportByUserIdAndDateBetween(Long id, LocalDate fromDate, LocalDate toDate){
		return inboundRepo.findByUserIdAndDateBetween(id, fromDate, toDate);
	}
	
	public int connectedCalls(Long userId, String connected, LocalTime time) {
		return inboundRepo.countByUserIdAndConnectionTypeAndAssignTime(userId, connected, time);
	}
	
	public int totalCalls(Long userId, LocalTime time) {
		return inboundRepo.countByUserIdAndAssignTime(userId, time);
	}
}
