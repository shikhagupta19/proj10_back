package com.rays.p10.dto;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rays.p10.common.BaseDTO;

@Entity
@Table(name = "SB_FACULTY")
public class FacultyDTO  extends BaseDTO{

	
	/**
     * firstName of Facultydto
     */
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	/**
     * lastName of Facultydto
     */
	@Column(name = "LAST_NAME")
	private String lastName;
	
	/**
     * gender of Facultydto
     */
	@Column(name = "GENDER")
	private String gender;
	
	/**
     * Naqualificationme of Facultydto
     */
	@Column(name = "QUALIFICATION")
	private String qualification;
	
	/**
     * mobileNo of Facultydto
     */
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
	/**
     * emailId of Facultydto
     */
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	/**
     * dob of Facultydto
     */
	@Column(name = "DOB")
	@JsonFormat(shape =JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
	private Date dob;
	
	/**
     * collegeId of Facultydto
     */
	@Column(name = "COLLEGE_ID")
	private Long collegeId;
	
	/**
     * courseId of Facultydto
     */
	@Column(name = "COURSE_ID")
	private Long courseId;
	
	/**
     * subjectId of Facultydto
     */
	@Column(name = "SUBJECT_ID")
	private Long subjectId;
	
	/**
     * collegeName of Facultydto
     */
	@Column(name = "COLLEGE_NAME")
	private String collegeName;
	
	/**
     * courseName of Facultydto
     */
	@Column(name = "COURSE_NAME")
	private String courseName;
	
	/**
     * subjectName of Facultydto
     */
	@Column(name = "SUBJECT_NAME")
	private String subjectName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	
	

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String>map= new LinkedHashMap<>();
		map.put("firstName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object>map= new LinkedHashMap<>();
		map.put("emailId", emailId);
		map.put("mobileNo", mobileNo);
		return map;
	}
	
	
}
