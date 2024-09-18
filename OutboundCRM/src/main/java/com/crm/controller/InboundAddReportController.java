package com.crm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.model.AssignTask;
import com.crm.model.InboundReport;
import com.crm.service.AssignTaskService;
import com.crm.service.CsvFileService;
import com.crm.service.InboundAddReportService;

import jakarta.servlet.http.HttpSession;

@Controller
public class InboundAddReportController {

	@Autowired
	private InboundAddReportService inboundAddReportService;

	@Autowired
	private CsvFileService csvFileService;

	@Autowired
	private AssignTaskService taskAssignService;

	// Fetching csv data and setting to input box for first time
	@GetMapping("/add-inbound")
	public String getCSVFileData(HttpSession session, Model model) throws NullPointerException {

		try {
			// Fetching min and max csv serial number
			String uid = session.getAttribute("loginUserId").toString();

			if (uid == null) {
				return "login";
			}
			long loggedInUserId = Long.parseLong(uid);
			model.addAttribute("userId", loggedInUserId);
			AssignTask task = taskAssignService.getAssignedTask(loggedInUserId);

			if (task != null) {

				long min = task.getMinSerialNumber();
				long max = task.getMaxSerialNumber();
				System.out.println("Min = "+min+" "+"Max = "+max);
				long currentSerialNumber = min; // Start from minSerialNumber
				// Fetching csv file data
				List<String[]> csvData = csvFileService.getCsvData();
				List<String[]> csvRows = getCsvRowsBySerialNumberRange(csvData, min, max);

				if ((currentSerialNumber <= max && currentSerialNumber <= csvRows.size())) {
					String[] csvRow = csvRows.get((int) (currentSerialNumber - 1)); // Fetch the current row
					// Note: Iterate this string and set one row to one model
					// Checking mobile number, called or not
					String checkCalledNumber = csvRow[3];
					System.out.println(checkCalledNumber);
					InboundReport savedMobileNumber = inboundAddReportService.findReportByMobile(checkCalledNumber);
					if (savedMobileNumber != null && savedMobileNumber.getMobile().equals(checkCalledNumber)) {
						model.addAttribute("assignedData",
								"The given below mobile number is already called. Thank You!");
						model.addAttribute("csvRow", csvRow); // Add the current row to the model
					} else {
						model.addAttribute("csvRow", csvRow); // Add the current row to the model
					}

					currentSerialNumber++;
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

	// Interate & fetch data and submit for 2nd time and rest all the time
	@PostMapping("/add-inbound")
	public String iterateAndSubmit(@ModelAttribute InboundReport report,
			@RequestParam("currentSerialNumber") long currentSNo, HttpSession session, Model model)
			throws NullPointerException, ArrayIndexOutOfBoundsException {

		try {
			// Fetching min and max csv serial number
			String uid = session.getAttribute("loginUserId").toString();
			if (uid == null) {
				return "login";
			}
			long loggedInUserId = Long.parseLong(uid);
			model.addAttribute("userId", loggedInUserId);
			AssignTask task = taskAssignService.getAssignedTask(loggedInUserId);

			if (task != null) {
				long min = task.getMinSerialNumber();
				long max = task.getMaxSerialNumber();
				System.out.println("Min = "+min+""+"Max = "+max);
				long currentSerialNumber = currentSNo; // Start from minSerialNumber
				// Fetching csv file data
				List<String[]> csvData = csvFileService.getCsvData();
				List<String[]> csvRows = getCsvRowsBySerialNumberRange(csvData, min, max);

				try {
					if (currentSerialNumber >= min && currentSerialNumber <= max
							&& (currentSerialNumber - min) < csvRows.size()) {
						String[] csvRow = csvRows.get((int) (currentSerialNumber - min)); // Fetch the current row

						// Validate csvRow length
						if (csvRow.length > 3) {
							// Checking mobile number, called or not
							String checkCalledNumber = csvRow[3];
							System.out.println(checkCalledNumber);
							InboundReport savedMobileNumber = inboundAddReportService
									.findReportByMobile(checkCalledNumber);
							if (savedMobileNumber != null && savedMobileNumber.getMobile().equals(checkCalledNumber)) {
								model.addAttribute("assignedData",
										"The given below mobile number is already called. Thank You!");
								model.addAttribute("csvRow", csvRow); // Add the current row to the model
							} else {
								model.addAttribute("csvRow", csvRow); // Add the current row to the model
							}
						} else {
							model.addAttribute("assignedData", "Thank You! You have done your job!");
						}

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
					System.out.println("No Record Found for mobile checking");
					inboundAddReportService.saveInboundReport(report);
				} else {
					if (inboundReportDb.getMobile().equals(report.getMobile())) {
						// Mobile number already exists, no need to save
					} else {
						inboundAddReportService.saveInboundReport(report);
					}
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

	
	// Getting csv data by serial number
	private List<String[]> getCsvRowsBySerialNumberRange(List<String[]> csvData, Long minSerialNumber,
			Long maxSerialNumber) {
		List<String[]> selectedRows = new ArrayList<>();
		for (int i = (int) (minSerialNumber - 1); i < maxSerialNumber && i < csvData.size(); i++) {
			selectedRows.add(csvData.get(i));
		}
		return selectedRows;
	}
	

}
