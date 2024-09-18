package com.crm.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crm.model.CsvFile;
import com.crm.repository.CsvFileRepository;

@Service
public class CsvFileService {

	@Autowired
	private CsvFileRepository csvFileRepo;

	private List<String[]> csvData = new ArrayList<>();

	public boolean uploadCsv(MultipartFile file) {

		csvData.clear(); // Clear previous data

		try {

			byte[] fileContent = file.getBytes(); // Read File Content

			CsvFile fileEntity = new CsvFile(file.getOriginalFilename(), file.getSize(), LocalDateTime.now(), fileContent); // Passing value as constructor

			csvFileRepo.save(fileEntity);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Fetching csv data by user id
	public List<String[]> getCsvData() {	
		long latestInsertedId = csvFileRepo.findMaxId();
		CsvFile file = csvFileRepo.findById(latestInsertedId).orElse(null);
		String csvData =new String(file.getFileContent(), StandardCharsets.UTF_8);
//		System.out.println(csvData);
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
}
