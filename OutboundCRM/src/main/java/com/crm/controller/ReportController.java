package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.crm.model.Report;
import com.crm.service.ReportService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	// Saving report
	@PostMapping("/save-report")
	public String saveReport(@ModelAttribute Report report, Model model, HttpSession session) {
		
		// Saving report data
		Report reportData = reportService.saveReportData(report);
		
		if(reportData!=null) {
			session.setAttribute("addSuccess", "successs");
		}
		
		// fetching list of report
		List<Report> ruralList = reportService.findRuralData();
		List<Report> urbanList = reportService.findUrbanData();
		model.addAttribute("ruralReports", ruralList);
		model.addAttribute("urbanReports", urbanList);
		
		return "view-report"; // This will hit only page directly
	}
	
	// Updating rural data
	@PostMapping("/update-rural-report/{id}")
	public String updateRuralData(@ModelAttribute @PathVariable Long id, Report report, Model model, HttpSession session) {
		boolean updateMsg = reportService.updateReport(id, report);
		if(updateMsg==true) {
			session.setAttribute("updateSuccess", "successs");
		}
		return "redirect:/view";  // return "redirect:/view" hit the mapping or link
	}
	
	// Delete Report
	@GetMapping("/deleteReport/{reportId}")
	public String deleteReport(@PathVariable Long reportId, Model model, HttpSession session) {
		boolean result = reportService.deleteReport(reportId);
		if(result == true) {
			session.setAttribute("deleteSuccess", "success");
		}
		return "redirect:/view";
	}
	
}
