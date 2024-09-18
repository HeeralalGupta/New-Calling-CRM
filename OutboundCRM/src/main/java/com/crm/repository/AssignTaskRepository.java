package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.model.AssignTask;

@Repository
public interface AssignTaskRepository extends JpaRepository<AssignTask, Long>{
	
	@Query(value = "SELECT * FROM task WHERE user_id = :id AND time = (SELECT MAX(time) FROM task WHERE user_id = :id)", nativeQuery = true)
	public AssignTask findByUserIdAndTime(Long id);
}
