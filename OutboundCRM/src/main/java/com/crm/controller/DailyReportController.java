package com.crm.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.model.User;
import com.crm.service.InboundAddReportService;
import com.crm.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DailyReportController {
	
	@Autowired
	private InboundAddReportService inboundService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/report-details")
	public String countReport(@RequestParam("id") Long id , Model model, HttpSession session) {
		Long countReportByIdAndDate = inboundService.countReportByIdAndDate(id);
		Long countReportById = inboundService.countReportById(id);
		User userById = userService.getUserById(id);
		String userName = userById.getName();
		String userEmail = userById.getEmail();
		System.out.println(countReportByIdAndDate);
		model.addAttribute("title", "Daily Report Details");
		model.addAttribute("userName", userName);
		model.addAttribute("userEmail", userEmail);
		model.addAttribute("countReportByDate", countReportByIdAndDate);
		model.addAttribute("countTotalReport", countReportById);
		
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    User userdb = userService.getUserById(userId);
	    
	    String userRole = userdb.getRole();
	   System.out.println(userRole);
	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }
	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
	    
		return "daily-report-details";
	}
	

	
}
