package com.crm.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.model.Feedback;
import com.crm.repository.FeedbackRepository;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackRepo;
	
	public Feedback saveFeedback(Feedback feedback) {
		feedback.setDate(LocalDate.now());
		return feedbackRepo.save(feedback);
	}
	
	public List<Feedback> findFeedback(){
		return feedbackRepo.findAll();
	}
	
	public boolean deleteFeedback(int id) {
		feedbackRepo.deleteById(id);
		return true;
	}
}
