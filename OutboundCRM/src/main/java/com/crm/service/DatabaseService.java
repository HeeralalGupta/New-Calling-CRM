package com.crm.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Database;
import com.crm.repository.DatabaseRepository;

@Service
@Transactional
public class DatabaseService {
	
	@Autowired
	private DatabaseRepository databaseRepository;
	
	public Database saveData(Database database) {
		database.setDate(LocalDate.now());
		database.setTime(LocalTime.now());
		return databaseRepository.save(database);
	}
	
	public List<Database> getAllData() {
		List<Database> allData = databaseRepository.findAll();
		return allData;
	}
	
	public Database findDataById(Integer id) {
		return databaseRepository.findById(id).orElse(null);
	}
	
}
