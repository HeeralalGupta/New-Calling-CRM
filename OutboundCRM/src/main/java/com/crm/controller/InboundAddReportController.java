package com.crm.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import jakarta.servlet.http.HttpServletResponse;
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
			// userProfile(session, model);
		    //======================= profile image end ======================
		    
			model.addAttribute("userId", loggedInUserId);
			AssignTask task = taskAssignService.getAssignedTask(loggedInUserId);

			if (task != null) {

				long min = task.getMinSerialNumber();
				long max = task.getMaxSerialNumber();
				
				long currentSerialNumber = min; // Start from minSerialNumber
				// Fetching csv file data
				List<String[]> csvData = taskAssignService.getCSVData(loggedInUserId);	
				List<String[]> csvRows = getCsvRowsBySerialNumberRange(csvData, min, max);
				//System.out.println("Initial values: currentSerialNumber = " + currentSerialNumber + ", max = " + max + ", csvRows.size() = " + csvRows.size());
				
				if ((currentSerialNumber <= max && csvRows.size() != 0)) {
					// Note: Iterate this string and set one row to one model
					String[] csvRow = csvRows.get((int) (0)); // Fetch the current row				
					// Checking mobile number, called or not

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
//				System.out.println("Min = "+min+""+"Max = "+max);
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
	                                          @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate, 
	                                          HttpSession session, HttpServletResponse response) {
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
	


	// ===================== Download report user wise ========================
	@GetMapping("generateReport/download")
	public ResponseEntity<Void> downloadReportByUserId(
	        @RequestParam("userId") Long userId,
	        @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
	        @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
	        HttpServletResponse response) {

			
			User userById = userService.getUserById(userId);
			String userName = userById.getName();
			
			List<InboundReport> reportData = inboundAddReportService.findReportByUserIdAndDateBetween(userId, fromDate, toDate);

			try {
				// Set content type and headers for the file download
	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + userName + ".xlsx\"");
				
				Workbook workbook = new XSSFWorkbook();
				Sheet sheet = workbook.createSheet("Report");
				
				Row headerRow = sheet.createRow(0);
				headerRow.createCell(0).setCellValue("S.No.");
				headerRow.createCell(1).setCellValue("Calling For");
				headerRow.createCell(2).setCellValue("Call Type");
				headerRow.createCell(3).setCellValue("Connected");
				headerRow.createCell(4).setCellValue("Not Connected");
				headerRow.createCell(5).setCellValue("Name");
				headerRow.createCell(6).setCellValue("Mobile No.");
				headerRow.createCell(7).setCellValue("Alternate Mobile");
				headerRow.createCell(8).setCellValue("Profession");
				headerRow.createCell(9).setCellValue("Email");
				headerRow.createCell(10).setCellValue("Age");
				headerRow.createCell(11).setCellValue("Gender");
				headerRow.createCell(12).setCellValue("District");
				headerRow.createCell(13).setCellValue("Block");
				headerRow.createCell(14).setCellValue("Panchayat");
				headerRow.createCell(15).setCellValue("Village");
				headerRow.createCell(16).setCellValue("Lok Sabha");
				headerRow.createCell(17).setCellValue("Vidhan Sabha");
				headerRow.createCell(18).setCellValue("Sub Division");
				headerRow.createCell(19).setCellValue("Booth No.");
				headerRow.createCell(20).setCellValue("Remarks");
				
				// Populate the data row
				int rowIdx = 1;
				for(InboundReport report : reportData) {
					Row row = sheet.createRow(rowIdx);
					row.createCell(0).setCellValue(rowIdx);
					row.createCell(1).setCellValue(report.getCallingFor());
					row.createCell(2).setCellValue(report.getConnectionType());
					row.createCell(3).setCellValue(report.getCallConnected());
					row.createCell(4).setCellValue(report.getCallNotConnected());
					row.createCell(5).setCellValue(report.getName());
					row.createCell(6).setCellValue(report.getMobile());
					row.createCell(7).setCellValue(report.getAlternateMobile());
					row.createCell(8).setCellValue(report.getProfession());
					row.createCell(9).setCellValue(report.getEmail());
					row.createCell(10).setCellValue(report.getAge());
					row.createCell(11).setCellValue(report.getGender());
					row.createCell(12).setCellValue(report.getRuralDistrict());
					row.createCell(13).setCellValue(report.getRuralBlock());
					row.createCell(14).setCellValue(report.getRuralPanchayat());
					row.createCell(15).setCellValue(report.getRuralVillage());
					row.createCell(16).setCellValue(report.getLokSabha());
					row.createCell(16).setCellValue(report.getVidhanSabha());
					row.createCell(17).setCellValue(report.getVidhanSabha());
					row.createCell(18).setCellValue(report.getSubDivision());
					row.createCell(19).setCellValue(report.getRuralWardNumber());
					row.createCell(20).setCellValue(report.getNote());
					rowIdx++;
				}
				workbook.write(response.getOutputStream());
				workbook.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				e.getMessage();
			}
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
	
	// ================== Download All Report ===================
	@GetMapping("/generateAllReport/download")
	public ResponseEntity<Void> findAllReport(@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
	        									@RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
	        									HttpServletResponse response) {
		
		List<InboundReport> allReport = inboundAddReportService.findAllReportByDateRange(fromDate, toDate);
		
		try {
			// Set content type and headers for the file download
            response.setContentType("application/vnd.ms-excel");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Report(" + fromDate + " to " + toDate + ").xlsx\"");
			
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Report");
			
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("S.No.");
			headerRow.createCell(1).setCellValue("Calling For");
			headerRow.createCell(2).setCellValue("Call Type");
			headerRow.createCell(3).setCellValue("Connected");
			headerRow.createCell(4).setCellValue("Not Connected");
			headerRow.createCell(5).setCellValue("Name");
			headerRow.createCell(6).setCellValue("Mobile No.");
			headerRow.createCell(7).setCellValue("Alternate Mobile");
			headerRow.createCell(8).setCellValue("Profession");
			headerRow.createCell(9).setCellValue("Email");
			headerRow.createCell(10).setCellValue("Age");
			headerRow.createCell(11).setCellValue("Gender");
			headerRow.createCell(12).setCellValue("District");
			headerRow.createCell(13).setCellValue("Block");
			headerRow.createCell(14).setCellValue("Panchayat");
			headerRow.createCell(15).setCellValue("Village");
			headerRow.createCell(16).setCellValue("Lok Sabha");
			headerRow.createCell(17).setCellValue("Vidhan Sabha");
			headerRow.createCell(18).setCellValue("Sub Division");
			headerRow.createCell(19).setCellValue("Booth No.");
			headerRow.createCell(20).setCellValue("Remarks");
			
			// Populate the data row
			int rowIdx = 1;
			for(InboundReport report : allReport) {
				Row row = sheet.createRow(rowIdx);
				row.createCell(0).setCellValue(rowIdx);
				row.createCell(1).setCellValue(report.getCallingFor());
				row.createCell(2).setCellValue(report.getConnectionType());
				row.createCell(3).setCellValue(report.getCallConnected());
				row.createCell(4).setCellValue(report.getCallNotConnected());
				row.createCell(5).setCellValue(report.getName());
				row.createCell(6).setCellValue(report.getMobile());
				row.createCell(7).setCellValue(report.getAlternateMobile());
				row.createCell(8).setCellValue(report.getProfession());
				row.createCell(9).setCellValue(report.getEmail());
				row.createCell(10).setCellValue(report.getAge());
				row.createCell(11).setCellValue(report.getGender());
				row.createCell(12).setCellValue(report.getRuralDistrict());
				row.createCell(13).setCellValue(report.getRuralBlock());
				row.createCell(14).setCellValue(report.getRuralPanchayat());
				row.createCell(15).setCellValue(report.getRuralVillage());
				row.createCell(16).setCellValue(report.getLokSabha());
				row.createCell(16).setCellValue(report.getVidhanSabha());
				row.createCell(17).setCellValue(report.getVidhanSabha());
				row.createCell(18).setCellValue(report.getSubDivision());
				row.createCell(19).setCellValue(report.getRuralWardNumber());
				row.createCell(20).setCellValue(report.getNote());
				rowIdx++;
			}
			workbook.write(response.getOutputStream());
			workbook.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
}
