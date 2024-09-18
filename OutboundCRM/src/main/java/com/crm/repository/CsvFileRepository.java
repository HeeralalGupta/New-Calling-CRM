package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.model.CsvFile;

@Repository
public interface CsvFileRepository extends JpaRepository<CsvFile, Long>{
	
//	@Query(value = "SELECT * FROM csvfile ORDER BY upload_time DESC", nativeQuery = true)
//    public CsvFile findLatestCsvData();
	@Query(value = "SELECT MAX(id) as id FROM csvfile", nativeQuery = true)
	public long findMaxId();
}
