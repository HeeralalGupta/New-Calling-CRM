package com.crm.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.AssignTask;

@Repository
@Transactional
public interface AssignTaskRepository extends JpaRepository<AssignTask, Long>{
	
	//	@Query(value = "SELECT * FROM task WHERE user_id = :id AND time = (SELECT MAX(time) FROM task WHERE user_id = :id)", nativeQuery = true)
	//	public AssignTask findByUserIdAndTime(Long id);
	
	// Native query to find the task by userId with the maximum time
    @Query(value = "SELECT * FROM task WHERE user_id = :userId ORDER BY time DESC LIMIT 1", nativeQuery = true)
    AssignTask findFirstByUserIdOrderByTimeDesc(@Param("userId") Long userId);
    
    @Query(value = "SELECT * FROM \"task\" r WHERE r.\"user_id\" = :userId AND r.\"date\" BETWEEN :fromDate AND :toDate", nativeQuery = true)
	public List<AssignTask> findByUserIdAndDateBetween(@Param("userId") Long userId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
	
    public AssignTask findByUserIdAndTime(Long userId, LocalTime time);
}
