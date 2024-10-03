package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.crm.model.Feedback;
import com.crm.service.FeedbackService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/saveFeedback")
	public String saveFeedback(@ModelAttribute Feedback feedback, HttpSession session) {
		Feedback saveFeedback = feedbackService.saveFeedback(feedback);
		session.setAttribute("successMessage", saveFeedback);
		return "redirect:/feedback";
	}
	@GetMapping("/deleteFeedback/{feedbackId}")
	public String deleteFeedback(@PathVariable int feedbackId) {
		feedbackService.deleteFeedback(feedbackId);
		return "redirect:/check-feedback";
	}
}
