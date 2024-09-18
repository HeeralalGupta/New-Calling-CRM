package com.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.model.InboundReport;

public interface InboundAddReportRepository extends JpaRepository<InboundReport, Long>{
	
	public InboundReport findByMobile(String mobile);
	public List<InboundReport> findByUserId(long id);
}
