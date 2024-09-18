package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.model.User;
import com.crm.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	public User adminLogin(boolean role, String email) {
		return loginRepository.findByIsAdminAndEmail(role, email);
	}
}
