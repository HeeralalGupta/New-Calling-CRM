package com.crm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class OtpService {
	
	Map<Long, String> otpStorage = new HashMap<>();
	
	public void saveOtp(long userId, String email) {
		otpStorage.put(userId, email);
	}
	
	public boolean verifyOtp(long userId, String otp) {
		String storedOtp = otpStorage.get(userId);
		return storedOtp != null && storedOtp.equals(otp);
	}
	
}
