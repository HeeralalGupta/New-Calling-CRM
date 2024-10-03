package com.crm.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.crm.model.AssignTask;
import com.crm.repository.AssignTaskRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class AssignTaskService {
	
	@Autowired
	private AssignTaskRepository assignTaskRepo;
	
	public AssignTask saveAssignedTask(AssignTask task, MultipartFile file) throws IOException{
		task.setDate(LocalDate.now());
		task.setTime(LocalTime.now());
		task.setFileName(file.getOriginalFilename());
		task.setFileSize(file.getSize());
		task.setFileContent(file.getBytes());
		return assignTaskRepo.save(task);
	}
	
	public AssignTask getAssignedTask(Long userId) {	
	    // Fetch the task by userId and max time
		AssignTask task = null;
		try {
		    // Fetch the task by userId and max time
		    task = assignTaskRepo.findFirstByUserIdOrderByTimeDesc(userId);

		    if (task != null) {
		    	// Convert byte array to CSV string
			    String csvData = new String(task.getFileContent(), StandardCharsets.UTF_8);
			    // Parse the CSV data
			    parseCsv(csvData);
		    }
		    
		} catch (RuntimeException e) {
		    // Handle specific runtime exceptions with better logging
		    System.err.println(e.getMessage());
		    e.printStackTrace();

		} catch (Exception e) {
		    // General exception handling
		    System.err.println("An unexpected error occurred: " + e.getMessage());
		    e.printStackTrace();
		}

	    return task; // Return the task object
	}


	public List<String[]> getCSVData(Long id) {
		AssignTask file = assignTaskRepo.findFirstByUserIdOrderByTimeDesc(id);
		String csvData =new String(file.getFileContent(), StandardCharsets.UTF_8);
		return parseCsv(csvData);
	}
	
	// Parsing csv data
		private List<String[]> parseCsv(String csvData) {
			List<String[]> records = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new StringReader(csvData))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					records.add(values);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return records;
		}
	
	public List<AssignTask> getAllAssignedTask(){
		return assignTaskRepo.findAll();
	}
	
	public boolean deleteByTaskId(long id) {
		assignTaskRepo.deleteById(id);
		return true;
	}
	
	public List<AssignTask> findReportByUserIdAndDateBetween(Long id, LocalDate fromDate, LocalDate toDate){
		return assignTaskRepo.findByUserIdAndDateBetween(id, fromDate, toDate);
	}
	
	// updating total calls and connected calls
	public AssignTask updateTask(Long userId, LocalTime time, int totalCalls, int connectedCalls) {
	    // Find the task by userId and time
	    AssignTask task = assignTaskRepo.findByUserIdAndTime(userId, time);

	    // Handle the case when no task is found
	    if (task == null) {
	        throw new EntityNotFoundException("Task not found for userId: " + userId + " at time: " + time);
	    }

	    // Update task details
	    task.setTotalCalls(totalCalls);
	    task.setConnectedCalls(connectedCalls);

	    // Save the updated task
	    return assignTaskRepo.save(task);
	}
	
}
