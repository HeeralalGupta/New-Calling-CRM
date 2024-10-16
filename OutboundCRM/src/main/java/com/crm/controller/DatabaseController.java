package com.crm.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crm.model.Database;
import com.crm.service.DatabaseService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class DatabaseController {
	
	@Autowired
	private DatabaseService databaseService;
	
	@PostMapping("/saveData")
	public String saveData(@Validated @ModelAttribute("database") Database database, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		database.setFileName(file.getOriginalFilename());
		database.setFileSize(file.getSize());
		database.setFileContent(file.getBytes());
		Database saveData = databaseService.saveData(database);
		if(saveData!=null) {
			session.setAttribute("saveData", saveData);
		}
		return "redirect:/upload-data";
	}
	
	// Download excel lob file from data base
	@PostMapping("/downlaodData")
	public void findDataById(@RequestParam("dataId") Integer dataId, HttpServletResponse response) throws IOException{
		Database excelData = databaseService.findDataById(dataId);
		if (excelData == null || excelData.getFileContent() == null) {
            response.sendError(HttpStatus.NOT_FOUND.value(), "File not found");
            return;
        }
		// Set the response headers
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + excelData.getFileName() + "\"");
        response.setContentLength(excelData.getFileSize().intValue()); // Set content length if available
		
        try (InputStream inputStream = new ByteArrayInputStream(excelData.getFileContent())) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
            response.getOutputStream().flush();
		}catch(Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            e.printStackTrace();
		}
		
	}
}
