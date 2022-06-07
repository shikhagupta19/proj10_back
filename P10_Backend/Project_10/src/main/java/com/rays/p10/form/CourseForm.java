package com.rays.p10.form;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.rays.p10.common.BaseDTO;
import com.rays.p10.common.BaseForm;
import com.rays.p10.dto.CourseDTO;

public class CourseForm extends BaseForm{

	
	@NotNull
	private String name;
	
	@NotNull
	private String duration;
	
	@NotNull
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDurration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	@Override
	public BaseDTO getDto() {
	
		System.out.println("inside GetDto in CourseForm--------");
		CourseDTO dto=initDTO(new CourseDTO()) ;
		
		dto.setName(name);
		dto.setDuration(duration);
		dto.setDescription(description);
		
		
		return dto;
	}
}
