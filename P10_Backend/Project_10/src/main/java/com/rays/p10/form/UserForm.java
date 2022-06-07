package com.rays.p10.form;

import java.util.Date;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.p10.common.BaseDTO;
import com.rays.p10.common.BaseForm;
import com.rays.p10.dto.UserDTO;

public class UserForm extends BaseForm{

	
	
	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotEmpty
	private String password;
	
	@NotEmpty
	@Email
	private String email;

	@NotNull
	private String status;

	
	private String roleName = null;

	@NotNull
	@Min(1)
	private Long roleId;

	@NotNull(message= "please enter phone")
	@Pattern(regexp="(^$|[0-9]{10})", message = "please enter 10 Digit valid Phoneno")
	private String phone;

//	@NotNull
	private Date dob;

	@NotEmpty
	private String gender;

	
	private String imagePath;

	//setter getter
	public String getFirstName() {
		return firstName;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public void setName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public BaseDTO getDto() {
		
		System.out.println("inside UserForm -------------------"
				+ "roleid===" +roleId);
		
		UserDTO dto = initDTO(new UserDTO());
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setEmail(email);
		
		dto.setPassword(password);
		dto.setRoleId(roleId);
		dto.setRoleName(roleName);
		dto.setDob(dob);
		dto.setGender(gender);
		dto.setPhone(phone);
		dto.setStatus(status);
		return dto;
	}
	
}
