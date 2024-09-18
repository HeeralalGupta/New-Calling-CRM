package com.crm.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private LocalDateTime time;
	private Long minSerialNumber;
	private Long maxSerialNumber;
	
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
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public AssignTask(Long id, String assignId, Long userId, String dataCategory, LocalDateTime time,
			Long minSerialNumber, Long maxSerialNumber) {
		super();
		this.id = id;
		this.assignId = assignId;
		this.userId = userId;
		this.dataCategory = dataCategory;
		this.time = time;
		this.minSerialNumber = minSerialNumber;
		this.maxSerialNumber = maxSerialNumber;
	}
	public AssignTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "AssignTask [id=" + id + ", assignId=" + assignId + ", userId=" + userId + ", dataCategory="
				+ dataCategory + ", time=" + time + ", minSerialNumber=" + minSerialNumber + ", maxSerialNumber="
				+ maxSerialNumber + "]";
	}
	
	
	
	
}
