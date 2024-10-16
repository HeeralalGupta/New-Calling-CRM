package com.crm.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Database {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    private String dataArea;
    private Long pinCode;
    private String category;
    private LocalDate date;
    private LocalTime time;
    private String fileName;
    private Long fileSize;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] fileContent;

    // No-argument constructor (required by Hibernate)
    public Database() {}

    // Constructor without id (since it's auto-generated)
    public Database(String dataArea, Long pinCode, String category, LocalDate date, LocalTime time,
                    String fileName, Long fileSize, byte[] fileContent) {
        super();
        this.dataArea = dataArea;
        this.pinCode = pinCode;
        this.category = category;
        this.date = date;
        this.time = time;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileContent = fileContent;
    }

    // Getter and setter for id
    public Integer getId() {
        return id;  // Changed to Integer
    }

    public void setId(Integer id) {
        this.id = id;  // Changed to Integer
    }

    // Other getters and setters
    public String getDataArea() {
        return dataArea;
    }

    public void setDataArea(String dataArea) {
        this.dataArea = dataArea;
    }

    public Long getPinCode() {
        return pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public String toString() {
        return "Database [id=" + id + ", dataArea=" + dataArea + ", pinCode=" + pinCode + ", category=" + category
                + ", date=" + date + ", time=" + time + ", fileName=" + fileName + ", fileSize=" + fileSize
                + ", fileContent=" + Arrays.toString(fileContent) + "]";
    }
}
