package com.rays.p10.form;

import java.util.Date;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.rays.p10.common.BaseDTO;
import com.rays.p10.common.BaseForm;
import com.rays.p10.dto.TimeTableDTO;

public class TimeTableForm extends BaseForm{

	
	@Min(1)
	@NotNull
private Long subjectId;
	
	
	@Min(1)
	@NotNull
	private Long courseId;
	
	
	private String subjectName;
	
	
	private String courseName;
	
	@NotNull
	private String description;
	
	@NotNull
	private Date examDate;
	
	@NotNull
	private String semester;
	
	@NotNull
	private String examTime;

	

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	
	
	@Override
		public BaseDTO getDto() {
		
	TimeTableDTO dto= initDTO(new TimeTableDTO()) ;
	
		dto.setSubjectId(subjectId);
		dto.setSubjectName(subjectName);
		dto.setExamDate(examDate);
		dto.setExamTime(examTime);
		dto.setCourseName(courseName);
		dto.setCourseId(courseId);
		dto.setSemester(semester);
		dto.setDescription(description);
	return dto;
	}
}
