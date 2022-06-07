package com.rays.p10.form;

import java.util.Date;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.p10.common.BaseDTO;
import com.rays.p10.common.BaseForm;
import com.rays.p10.dto.FacultyDTO;

public class FacultyForm extends BaseForm{

	
	@NotNull
	private String firstName;
	
	
	@NotNull
	private String lastName;
	
	
	@NotNull
	private String gender;
	
	
	@NotNull
	private String qualification;
	
	@NotNull
	@Pattern(regexp = "\\d{10}", message = "invalid Mobile_No")
	private String mobileNo;
	
	
	@NotNull
	private String emailId;
	
	@NotNull
	private Date dob;
	
	
	@NotNull
	@Min(1)
	private Long collegeId;
	
	@NotNull
	@Min(1)
	private Long courseId;
	
	@NotNull
	@Min(1)
	private Long subjectId;
	
	private String collegeName;
	
	private String courseName;
	
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

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
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
	public BaseDTO getDto() {
	
		System.out.println("inside faculty getDto-----------  "
				+ "fname" + collegeId);
		FacultyDTO dto=initDTO (new FacultyDTO());

		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setEmailId(emailId);
		dto.setMobileNo(mobileNo);
		dto.setGender(gender);
		dto.setDob(dob);
		dto.setCollegeId(collegeId);
		dto.setCourseId(courseId);
		dto.setSubjectId(subjectId);
		dto.setSubjectName(subjectName);
		dto.setCollegeName(collegeName);
		dto.setCourseName(courseName);
		dto.setQualification(qualification);
		
		return dto;
	}
}
