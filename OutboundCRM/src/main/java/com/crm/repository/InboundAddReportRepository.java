package com.crm.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crm.model.InboundReport;

public interface InboundAddReportRepository extends JpaRepository<InboundReport, Long>{
	
	public InboundReport findByMobile(String mobile);
	
	public List<InboundReport> findByUserIdAndDate(long id, LocalDate date);
	
	@Query(value = "SELECT COUNT(*) FROM \"inbound-report\" WHERE user_id = ?1 AND date = ?2", nativeQuery = true)
	public Long countByIdAndDate(Long id, LocalDate date);
	
	@Query(value = "SELECT COUNT(*) FROM \"inbound-report\" WHERE user_id = ?1", nativeQuery = true)
	public Long countById(Long id);

}
