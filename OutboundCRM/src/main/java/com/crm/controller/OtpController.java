package com.crm.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.model.Response;
import com.crm.service.EmailService;
import com.crm.service.OtpService;

@Controller
public class OtpController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OtpService otpService;
	
	@GetMapping("/sendOtp")
	public Response sendOtp(@RequestParam("userId") long userId, @RequestParam("email") String email) {
		
		String otp = generateOtp();
		
		otpService.saveOtp(userId, otp);	
		emailService.sendOtpEmail(email, otp);
		
		return new Response(true, "OTP send to mail");
	}

	private String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}
	
	
}
