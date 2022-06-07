package com.rays.p10.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.p10.common.BaseDTO;
import com.rays.p10.common.BaseForm;
import com.rays.p10.dto.StudentDTO;

public class StudentForm extends BaseForm{

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	
	private Date dob;

	@NotEmpty
	@Pattern(regexp = "\\d{10}", message = "invalid Mobile_No")
	private String mobileNo;

	@NotEmpty
	@Email
	private String email;

	private String collegeName;

	@NotNull(message = "CollegeName must not be null")
	private Long collegeId;

	@NotNull
	private String enrolNo;
	
	//seeter getter
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public String getEnrolNo() {
		return enrolNo;
	}

	public void setEnrolNo(String enrolNo) {
		this.enrolNo = enrolNo;
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

	

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	
	@Override
	public BaseDTO getDto() {
	
		StudentDTO dto = initDTO(new StudentDTO());
		dto.setCollegeId(collegeId);
		dto.setEmail(email);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setMobileNo(mobileNo);
		dto.setCollegeName(collegeName);
		dto.setDob(dob);
		dto.setEnrolNo(enrolNo);
		return dto;
	}
	
	
	
}
