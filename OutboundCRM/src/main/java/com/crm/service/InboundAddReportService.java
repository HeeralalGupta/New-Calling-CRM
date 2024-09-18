package com.crm.service;

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
		return inboundRepo.save(report);
	}
	
	// finding inbound report by mobile number
	public InboundReport findReportByMobile(String mobile) {
		return inboundRepo.findByMobile(mobile);
	}
	
	// finding caller details by caller id 
	public List<InboundReport> findCalledData(long id) {
		return inboundRepo.findByUserId(id);
	}
}
