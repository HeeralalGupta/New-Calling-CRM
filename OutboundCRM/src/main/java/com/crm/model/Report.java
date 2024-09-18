package com.crm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report")
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String connectionType;
	private String callConnected;
	private String callNotConnected;
	private String name;
	private String mobile;
	private String alternateMobile;
	private String email;
	private String profession;
	private String gender;
	private int age;
	private String residentialType;
	private String ruralDistrict;
	private String ruralBlock;
	private String ruralPanchayat;
	private String ruralVillage;
	private int ruralWardNumber;
	private String urbanDistrict;
	private String urbanPoliceStation;
	private String urbanMunicipality;
	private String lokSabha;
	private String vidhanSabha;
	private String subDivision;
	private String callingFor;
	private String note;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	public String getCallConnected() {
		return callConnected;
	}
	public void setCallConnected(String callConnected) {
		this.callConnected = callConnected;
	}
	public String getCallNotConnected() {
		return callNotConnected;
	}
	public void setCallNotConnected(String callNotConnected) {
		this.callNotConnected = callNotConnected;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAlternateMobile() {
		return alternateMobile;
	}
	public void setAlternateMobile(String alternateMobile) {
		this.alternateMobile = alternateMobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getResidentialType() {
		return residentialType;
	}
	public void setResidentialType(String residentialType) {
		this.residentialType = residentialType;
	}
	public String getRuralDistrict() {
		return ruralDistrict;
	}
	public void setRuralDistrict(String ruralDistrict) {
		this.ruralDistrict = ruralDistrict;
	}
	public String getRuralBlock() {
		return ruralBlock;
	}
	public void setRuralBlock(String ruralBlock) {
		this.ruralBlock = ruralBlock;
	}
	public String getRuralPanchayat() {
		return ruralPanchayat;
	}
	public void setRuralPanchayat(String ruralPanchayat) {
		this.ruralPanchayat = ruralPanchayat;
	}
	public String getRuralVillage() {
		return ruralVillage;
	}
	public void setRuralVillage(String ruralVillage) {
		this.ruralVillage = ruralVillage;
	}
	public int getRuralWardNumber() {
		return ruralWardNumber;
	}
	public void setRuralWardNumber(int ruralWardNumber) {
		this.ruralWardNumber = ruralWardNumber;
	}
	public String getUrbanDistrict() {
		return urbanDistrict;
	}
	public void setUrbanDistrict(String urbanDistrict) {
		this.urbanDistrict = urbanDistrict;
	}
	public String getUrbanPoliceStation() {
		return urbanPoliceStation;
	}
	public void setUrbanPoliceStation(String urbanPoliceStation) {
		this.urbanPoliceStation = urbanPoliceStation;
	}
	public String getUrbanMunicipality() {
		return urbanMunicipality;
	}
	public void setUrbanMunicipality(String urbanMunicipality) {
		this.urbanMunicipality = urbanMunicipality;
	}
	public String getLokSabha() {
		return lokSabha;
	}
	public void setLokSabha(String lokSabha) {
		this.lokSabha = lokSabha;
	}
	public String getVidhanSabha() {
		return vidhanSabha;
	}
	public void setVidhanSabha(String vidhanSabha) {
		this.vidhanSabha = vidhanSabha;
	}
	public String getSubDivision() {
		return subDivision;
	}
	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}
	public String getCallingFor() {
		return callingFor;
	}
	public void setCallingFor(String callingFor) {
		this.callingFor = callingFor;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Report(Long id, String connectionType, String callConnected, String callNotConnected, String name,
			String mobile, String alternateMobile, String email, String profession, String gender, int age,
			String residentialType, String ruralDistrict, String ruralBlock, String ruralPanchayat, String ruralVillage,
			int ruralWardNumber, String urbanDistrict, String urbanPoliceStation, String urbanMunicipality,
			String lokSabha, String vidhanSabha, String subDivision, String callingFor, String note) {
		super();
		Id = id;
		this.connectionType = connectionType;
		this.callConnected = callConnected;
		this.callNotConnected = callNotConnected;
		this.name = name;
		this.mobile = mobile;
		this.alternateMobile = alternateMobile;
		this.email = email;
		this.profession = profession;
		this.gender = gender;
		this.age = age;
		this.residentialType = residentialType;
		this.ruralDistrict = ruralDistrict;
		this.ruralBlock = ruralBlock;
		this.ruralPanchayat = ruralPanchayat;
		this.ruralVillage = ruralVillage;
		this.ruralWardNumber = ruralWardNumber;
		this.urbanDistrict = urbanDistrict;
		this.urbanPoliceStation = urbanPoliceStation;
		this.urbanMunicipality = urbanMunicipality;
		this.lokSabha = lokSabha;
		this.vidhanSabha = vidhanSabha;
		this.subDivision = subDivision;
		this.callingFor = callingFor;
		this.note = note;
	}
	@Override
	public String toString() {
		return "Report [Id=" + Id + ", connectionType=" + connectionType + ", callConnected=" + callConnected
				+ ", callNotConnected=" + callNotConnected + ", name=" + name + ", mobile=" + mobile
				+ ", alternateMobile=" + alternateMobile + ", email=" + email + ", profession=" + profession
				+ ", gender=" + gender + ", age=" + age + ", residentialType=" + residentialType + ", ruralDistrict="
				+ ruralDistrict + ", ruralBlock=" + ruralBlock + ", ruralPanchayat=" + ruralPanchayat
				+ ", ruralVillage=" + ruralVillage + ", ruralWardNumber=" + ruralWardNumber + ", urbanDistrict="
				+ urbanDistrict + ", urbanPoliceStation=" + urbanPoliceStation + ", urbanMunicipality="
				+ urbanMunicipality + ", lokSabha=" + lokSabha + ", vidhanSabha=" + vidhanSabha + ", subDivision="
				+ subDivision + ", callingFor=" + callingFor + ", note=" + note + "]";
	}
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
