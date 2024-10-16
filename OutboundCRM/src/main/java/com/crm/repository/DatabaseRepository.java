package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.model.Database;

public interface DatabaseRepository extends JpaRepository<Database, Integer> {
	
}
