package com.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.model.Report;
import com.crm.repository.ReportRepository;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepository;
	
	// Saving report data
	public Report saveReportData(Report report) {
		try {
			return reportRepository.save(report);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// Finding rural data
	public List<Report> findRuralData() {
		List<Report> ruralList = reportRepository.findByRuralData();
		return ruralList;
	}
	
	// Finding urban data
	public List<Report> findUrbanData(){
		List<Report> urbanList = reportRepository.findByUrbanData();
		return urbanList;
	}
	
	// Finding report by id
	public Report findReport(Long Id) {
		Optional<Report> findById = reportRepository.findById(Id);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	
	// Update report by id
	public boolean updateReport(Long id, Report reportData) {
		Optional<Report> optional = reportRepository.findById(id);
		if(optional.isPresent()) {
			Report report = optional.get();
			report.setConnectionType(reportData.getConnectionType());
			report.setCallConnected(reportData.getCallConnected());
			report.setCallNotConnected(reportData.getCallNotConnected());
			report.setName(reportData.getName());
			report.setMobile(reportData.getMobile());
			report.setAlternateMobile(reportData.getAlternateMobile());
			report.setEmail(reportData.getEmail());
			report.setProfession(reportData.getProfession());
			report.setGender(report.getGender());
			report.setAge(reportData.getAge());
			report.setUrbanPoliceStation(reportData.getUrbanPoliceStation());
			report.setUrbanMunicipality(reportData.getUrbanMunicipality());
			report.setResidentialType(reportData.getResidentialType());
			report.setRuralDistrict(reportData.getRuralDistrict());
			report.setRuralBlock(reportData.getRuralBlock());
			report.setRuralPanchayat(reportData.getRuralPanchayat());
			report.setRuralVillage(reportData.getRuralVillage());
			report.setLokSabha(reportData.getLokSabha());
			report.setVidhanSabha(reportData.getVidhanSabha());
			report.setSubDivision(reportData.getSubDivision());
			report.setCallingFor(reportData.getCallingFor());
			report.setRuralWardNumber(reportData.getRuralWardNumber());
			report.setNote(reportData.getNote());
			
			Report save = reportRepository.save(report);
			if(save!=null) {
				return true;
			}
			
		}
		return false;
	}
	
	// Delete Report
	public boolean deleteReport(Long id) {
		reportRepository.deleteById(id);
		return true;
	}
	
}
