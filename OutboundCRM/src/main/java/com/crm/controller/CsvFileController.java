package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crm.model.CsvFile;
import com.crm.service.CsvFileService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CsvFileController {
	
	@Autowired
	private CsvFileService csvFileService;
	
	 // Handle file upload
    @PostMapping("/uploadCsv")
    public String uploadCsv(@RequestParam("file") MultipartFile file, CsvFile csv, Model model, HttpSession session) {
        if (csvFileService.uploadCsv(file)); {
            session.setAttribute("message", "File uploaded and saved successfully.");
        }
        return "redirect:/add-csv";
    }
    
    
}
