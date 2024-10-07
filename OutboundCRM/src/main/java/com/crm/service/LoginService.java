package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.User;
import com.crm.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Transactional
	public User adminLogin(String role, String email) {
		return loginRepository.findByRoleAndEmail(role, email);
	}
}
