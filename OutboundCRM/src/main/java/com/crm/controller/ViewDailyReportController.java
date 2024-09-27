package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.model.User;
import com.crm.service.InboundAddReportService;
import com.crm.service.UserService;

@Controller
public class ViewDailyReportController {
	
	@Autowired
	private InboundAddReportService inboundService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/countByDate")
	public String countReport(@RequestParam("id") Long id , Model model) {
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
		return "daily-report-details";
	}
}
