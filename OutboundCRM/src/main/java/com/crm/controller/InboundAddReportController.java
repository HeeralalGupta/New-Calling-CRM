package com.crm.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.model.AssignTask;
import com.crm.model.InboundReport;
import com.crm.model.User;
import com.crm.service.AssignTaskService;
import com.crm.service.InboundAddReportService;
import com.crm.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class InboundAddReportController {

	@Autowired
	private InboundAddReportService inboundAddReportService;

//	@Autowired
//	private CsvFileService csvFileService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AssignTaskService taskAssignService;

	// Fetching csv data and setting to input box for first time
	@GetMapping("/add-inbound")
	public String getCSVFileData(HttpSession session, Model model) throws NullPointerException {
		
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		
		try {
			// Fetching min and max csv serial number
			String uid = session.getAttribute("loginUserId").toString();		
			Long loggedInUserId = Long.parseLong(uid);
			
			//======================== for profile image ==================
//		    userProfile(session, model);
		    //======================= profile image end ======================
		    
			model.addAttribute("userId", loggedInUserId);
			AssignTask task = taskAssignService.getAssignedTask(loggedInUserId);

			if (task != null) {

				long min = task.getMinSerialNumber();
				long max = task.getMaxSerialNumber();
				System.out.println("Min = "+min+""+"Max = "+max);
				
				long currentSerialNumber = min; // Start from minSerialNumber
				// Fetching csv file data
				List<String[]> csvData = taskAssignService.getCSVData(loggedInUserId);	
				List<String[]> csvRows = getCsvRowsBySerialNumberRange(csvData, min, max);
				System.out.println("Initial values: currentSerialNumber = " + currentSerialNumber + ", max = " + max + ", csvRows.size() = " + csvRows.size());
				
				if ((currentSerialNumber <= max && csvRows.size() != 0)) {
					// Note: Iterate this string and set one row to one model
					String[] csvRow = csvRows.get((int) (0)); // Fetch the current row				
					// Checking mobile number, called or not
					System.out.println("Hitting first method");

					model.addAttribute("csvRow", csvRow); // Add the current row to the model
					currentSerialNumber++;
				}else {
					model.addAttribute("assignedData","No data assigned for you");
				}
				
				model.addAttribute("currentSerialNumber", currentSerialNumber);
				model.addAttribute("tcId", task.getAssignId());
				// model.addAttribute("csvRows", csvRows);

			} else {
				model.addAttribute("assignedData", "No data assigned for you");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "add-inbound-report";
	}

	// Iterate & fetch data and submit for 2nd time and rest all the time
	@PostMapping("/add-inbound")
	public String iterateAndSubmit(@ModelAttribute InboundReport report,
			@RequestParam("currentSerialNumber") long currentSNo, HttpSession session, Model model)
			throws NullPointerException, ArrayIndexOutOfBoundsException, NumberFormatException {
		System.out.println("Hitting first method");
		try {
			// Fetching min and max csv serial number
			String uid = session.getAttribute("userSession").toString();
			if (uid == null) {
				return "login";
			}
			Long loggedInUserId = Long.parseLong(uid);
			
			// getting user details from the database  
		    User userdb = userService.getUserById(loggedInUserId);

		    // If user data is present, encode the profile image to base64
		    //userProfile(session, model);

		    // Add user details and title to the model
		    model.addAttribute("userProfile", userdb);
			
			model.addAttribute("userId", loggedInUserId);
			AssignTask task = taskAssignService.getAssignedTask(loggedInUserId);

			if (task != null) {
				long min = task.getMinSerialNumber();
				long max = task.getMaxSerialNumber();
				System.out.println("Min = "+min+""+"Max = "+max);
				long currentSerialNumber = currentSNo; // Start from minSerialNumber
				// Fetching csv file data
				List<String[]> csvData = taskAssignService.getCSVData(loggedInUserId);
				List<String[]> csvRows = getCsvRowsBySerialNumberRange(csvData, min, max);

				try {
					if (currentSerialNumber >= min && currentSerialNumber <= max && (currentSerialNumber - min) < csvRows.size()) {
						String[] csvRow = csvRows.get((int) (currentSerialNumber - min)); // Fetch the current row
						System.out.println("Hitting second method");
						
						model.addAttribute("csvRow", csvRow); // Add the current row to the model

						currentSerialNumber++; // Increment for next row
					} else {
						model.addAttribute("assignedData", "Thank you! You have completed your task.");
					}
				} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
				}

				model.addAttribute("currentSerialNumber", currentSerialNumber);
				model.addAttribute("minSerialNumber", min);
				model.addAttribute("tcId", task.getAssignId());
				// model.addAttribute("csvRows", csvRows);

			} else {
				model.addAttribute("assignedData", "Thank You! You have done your job!");
			}

			// Finding report data by mobile number
			try {
				InboundReport inboundReportDb = inboundAddReportService.findReportByMobile(report.getMobile()); // Finding inbound report data
																												
				// verifying mobile number, already saved in db or not
				if (inboundReportDb == null) {
					AssignTask assignedTask = taskAssignService.getAssignedTask(loggedInUserId);
					report.setAssignTime(assignedTask.getTime());
					inboundAddReportService.saveInboundReport(report);
					
					// Finding connected call and total calls from inbound report table
					int connectedCalls = inboundAddReportService.connectedCalls(loggedInUserId, "connected", assignedTask.getTime());
					int totalCalls = inboundAddReportService.totalCalls(loggedInUserId, assignedTask.getTime());
					System.out.println("connected calls : "+connectedCalls);
					System.out.println("total calls : "+totalCalls);
					
					// Now Update Task Table with total calls and connected calls
					taskAssignService.updateTask(loggedInUserId, assignedTask.getTime(), totalCalls, connectedCalls);
				} else {
					System.out.println("Mobile number already exist in database");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add-inbound-report";
	}
	
//	=========================================== Generating report by user id and date between ==================

	@PostMapping("/generateReport")
	@ResponseBody // Ensures that the List<InboundReport> is returned as JSON, so it can be processed by jQuery.
	public List<AssignTask> generateReport(@RequestParam("userId") Long userId,
	                                          @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
	                                          @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate, HttpSession session) {
	    // Fetch the report data using the service method
	    List<AssignTask> list = taskAssignService.findReportByUserIdAndDateBetween(userId, fromDate, toDate);
	    // Returning the list as JSON   
	    session.setAttribute("generateUserId", userId);
	    return list;
	}


//	=========================================== Generating report by user id and date between ==================

	
	// Getting csv data by serial number
	private List<String[]> getCsvRowsBySerialNumberRange(List<String[]> csvData, Long minSerialNumber, Long maxSerialNumber) {
	    List<String[]> selectedRows = new ArrayList<>();
	    
	    for (int i = (int) (minSerialNumber - 1); i < maxSerialNumber && i < csvData.size(); i++) {
	        String[] row = csvData.get(i);
	        String mobileNumberFromCsv = row[3]; // Column 3 corresponds to index 2 in 0-based index
	        
	        // Find the report by mobile number from CSV
	        InboundReport savedMobileNumber = inboundAddReportService.findReportByMobile(mobileNumberFromCsv);
	        
	        // Check if the mobile number in the row is different from the one in the database
	        if (savedMobileNumber == null || !mobileNumberFromCsv.equals(savedMobileNumber.getMobile())) {
	            selectedRows.add(row);
	        }
	    }
	    
	    return selectedRows;
	}


	
	//======================== for profile image ==================
	private void userProfile(HttpSession session, Model model) {
		
		String uid = session.getAttribute("loginUserId").toString();		
		Long loggedInUserId = Long.parseLong(uid);
		// getting user details from the database  
	    User userdb = userService.getUserById(loggedInUserId);

	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }

	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
	    
	}
	//======================= profile image end ======================
	

}
