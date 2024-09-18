package com.crm.model;

import java.time.LocalDateTime;
import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="csvfile")
public class CsvFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fileName;
	private Long fileSize;
	private LocalDateTime uploadTime;
	@Lob
	private byte[] fileContent;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public LocalDateTime getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(LocalDateTime uploadTime) {
		this.uploadTime = uploadTime;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	public CsvFile(Long id, String fileName, Long fileSize, LocalDateTime uploadTime, byte[] fileContent) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.uploadTime = uploadTime;
		this.fileContent = fileContent;
	}
	public CsvFile(String fileName, Long fileSize, LocalDateTime uploadTime, byte[] fileContent) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.uploadTime = uploadTime;
		this.fileContent = fileContent;
	}
	public CsvFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CsvFile [id=" + id + ", fileName=" + fileName + ", fileSize=" + fileSize + ", uploadTime=" + uploadTime
				+ ", fileContent=" + Arrays.toString(fileContent) + "]";
	}
	
	
	
	
}
