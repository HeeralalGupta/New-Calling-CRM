package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.model.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long>{
	public User findByIsAdminAndEmail(boolean role, String email);
}
