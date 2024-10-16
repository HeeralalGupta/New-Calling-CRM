package com.crm.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.crm.model.AssignTask;
import com.crm.model.User;
import com.crm.service.AssignTaskService;
import com.crm.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AssignTaskController {
	
	@Autowired
	private AssignTaskService assignTaskService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/assignTask")
	public String saveAssigendTask(@ModelAttribute AssignTask task, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		Long userId = task.getUserId();
		User byId = userService.getUserById(userId);
		task.setUserName(byId.getName());
		AssignTask saveAssignedTask = assignTaskService.saveAssignedTask(task, file);
		if(saveAssignedTask!=null) {
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
	
	@PostMapping("/generateReportByDateRange")
	@ResponseBody
	public List<AssignTask> findReportByDateRange(@RequestParam("startDate") @DateTimeFormat( pattern = "yyyy-MM-dd") LocalDate startDate, 
										@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		List<AssignTask> list = assignTaskService.findAllReportByDate(startDate, endDate);
		return list;
	}
	
	
	
	
}
