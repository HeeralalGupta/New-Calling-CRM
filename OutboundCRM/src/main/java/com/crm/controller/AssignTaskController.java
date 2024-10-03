package com.crm.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crm.model.AssignTask;
import com.crm.service.AssignTaskService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AssignTaskController {
	
	@Autowired
	private AssignTaskService assignTaskService;
	
	@PostMapping("/assignTask")
	public String saveAssigendTask(@ModelAttribute AssignTask task, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		System.out.println(task.getAssignId());
		AssignTask saveAssignedTask = assignTaskService.saveAssignedTask(task, file);
		if(saveAssignedTask!=null) {
			System.out.println("Task Assigned Success");
			session.setAttribute("assigned", saveAssignedTask);
			return "redirect:/add-csv";
		}else {
			session.setAttribute("assigned", saveAssignedTask);
			return "redirect:/add-csv";
		}
	}
	
	@GetMapping("/deleteTask/{taskId}")
	public String deleteTask(@PathVariable long taskId) {
		assignTaskService.deleteByTaskId(taskId);
		return "redirect:/view-task";
	}
}
