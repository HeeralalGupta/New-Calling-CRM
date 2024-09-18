package com.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crm.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
	
	@Query(value = "SELECT * FROM report WHERE residential_type = 'Urban'", nativeQuery = true)
	List<Report> findByUrbanData();
	
	@Query(value = "SELECT * FROM report WHERE residential_type = 'Rural'", nativeQuery = true)
	List<Report> findByRuralData();
}
