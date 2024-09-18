package com.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
//	@Query(value = "select * from users where is_admin=false", nativeQuery=true)
	public List<User> findByIsAdmin(boolean checkUser);
	
}
