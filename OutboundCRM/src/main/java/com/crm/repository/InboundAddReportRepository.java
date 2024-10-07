package com.crm.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crm.model.InboundReport;

public interface InboundAddReportRepository extends JpaRepository<InboundReport, Long>{
	
	public InboundReport findByMobile(String mobile);
	
	public List<InboundReport> findByUserIdAndDate(long id, LocalDate date);
	
	@Query(value = "SELECT COUNT(*) FROM \"inbound-report\" WHERE user_id = ?1 AND date = ?2", nativeQuery = true)
	public Long countByIdAndDate(Long id, LocalDate date);
	
	@Query(value = "SELECT COUNT(*) FROM \"inbound-report\" WHERE user_id = ?1", nativeQuery = true)
	public Long countById(Long id);
	
	public Long countByUserId(Long userId);
	
	// counting connected data on daily basis
	@Query(value = "SELECT COUNT(*) FROM \"inbound-report\" WHERE user_id = :userId AND connection_type = :connectionType AND date = :date", nativeQuery = true)
	Long countByUserIdAndConnectionTypeAndDateAndAssignTime(Long userId, String connectionType, LocalDate date);
	
	@Query(value = "SELECT * FROM \"inbound-report\" r WHERE r.\"user_id\" = :userId AND r.\"date\" BETWEEN :fromDate AND :toDate", nativeQuery = true)
	public List<InboundReport> findByUserIdAndDateBetween(@Param("userId") Long userId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
	
	@Query(value = "SELECT COUNT(*) FROM \"inbound-report\" WHERE user_id = ?1 AND assign_time = ?2", nativeQuery = true)
	public int countByUserIdAndAssignTime(Long userId, LocalTime time);
	
	// counting connected data with specific time for updating task table
	int countByUserIdAndConnectionTypeAndAssignTime(Long userId, String connectionType, LocalTime time);
	
}
