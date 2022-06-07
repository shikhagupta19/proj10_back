package com.rays.p10.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.rays.p10.common.BaseDTO;
import com.rays.p10.common.BaseForm;
import com.rays.p10.dto.SubjectDTO;

public class SubjectForm extends BaseForm{

	@NotNull
	private String subjectName;

	@NotNull
	private String description;

	@Min(1)
	private long courseId;

	
	private String courseName;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	////////////////////////////////////
	
	@Override
	public BaseDTO getDto() {
	
		
		System.out.println("inside getDto in SubjectDTO-------------------");
		SubjectDTO dto=initDTO(new SubjectDTO());
		
		dto.setSubjectName(subjectName);
		dto.setDescription(description);
		dto.setCourseId(courseId);
		dto.setCourseName(courseName);
		
		
		return dto;
	}
	
}
