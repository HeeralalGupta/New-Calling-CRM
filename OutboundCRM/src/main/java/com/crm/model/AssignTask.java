package com.crm.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "task")
public class AssignTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String assignId;
	private Long userId;
	private String dataCategory;
	private Long pinCode;
	private LocalDate date;
	private LocalTime time;
	private Long minSerialNumber;
	private Long maxSerialNumber;
	@Column(name="total_calls", nullable = false)
	private Integer totalCalls=0;
	@Column(name="connected_calls", nullable = false)
	private Integer connectedCalls=0;
	private String callingAreaName;
	private String fileName;
	private Long fileSize;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] fileContent;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAssignId() {
		return assignId;
	}
	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getMinSerialNumber() {
		return minSerialNumber;
	}
	public void setMinSerialNumber(Long minSerialNumber) {
		this.minSerialNumber = minSerialNumber;
	}
	public Long getMaxSerialNumber() {
		return maxSerialNumber;
	}
	public void setMaxSerialNumber(Long maxSerialNumber) {
		this.maxSerialNumber = maxSerialNumber;
	}
	public String getDataCategory() {
		return dataCategory;
	}
	public void setDataCategory(String dataCategory) {
		this.dataCategory = dataCategory;
	}
	
	public String getCallingAreaName() {
		return callingAreaName;
	}
	public void setCallingAreaName(String callingAreaName) {
		this.callingAreaName = callingAreaName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	
	public Long getPinCode() {
		return pinCode;
	}
	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}
	public int getTotalCalls() {
		return totalCalls;
	}
	public void setTotalCalls(int totalCalls) {
		this.totalCalls = totalCalls;
	}
	public int getConnectedCalls() {
		return connectedCalls;
	}
	public void setConnectedCalls(int connectedCalls) {
		this.connectedCalls = connectedCalls;
	}
	public AssignTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AssignTask(Long id, String assignId, Long userId, Long pinCode, String dataCategory, LocalDate date, LocalTime time,
			Long minSerialNumber, Long maxSerialNumber, String callingAreaName, String fileName, Long fileSize,
			byte[] fileContent) {
		super();
		this.id = id;
		this.assignId = assignId;
		this.userId = userId;
		this.pinCode = pinCode;
		this.dataCategory = dataCategory;
		this.date = date;
		this.time = time;
		this.minSerialNumber = minSerialNumber;
		this.maxSerialNumber = maxSerialNumber;
		this.callingAreaName = callingAreaName;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileContent = fileContent;

	}
	@Override
	public String toString() {
		return "AssignTask [id=" + id + ", assignId=" + assignId + ", userId=" + userId + ", dataCategory="
				+ dataCategory + ", date=" + date + ", time=" + time + ", minSerialNumber=" + minSerialNumber
				+ ", maxSerialNumber=" + maxSerialNumber + ", callingAreaName=" + callingAreaName + ", fileName="
				+ fileName + ", fileSize=" + fileSize + ", fileContent=" + Arrays.toString(fileContent) + "]";
	}
	

	
}
