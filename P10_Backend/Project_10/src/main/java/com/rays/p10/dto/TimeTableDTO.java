package com.rays.p10.dto;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.rays.p10.common.BaseDTO;

@Entity
@Table(name = "SB_TIMETABLE")
public class TimeTableDTO extends BaseDTO{

	@Column(name = "SUBJECT_ID")
	private Long subjectId;
	
	@Column(name = "COURSE_ID")
	private Long courseId;
	
	@Column(name = "SUBJECT_NAME")
	private String subjectName;
	
	@Column(name = "COURSE_NAME")
	private String courseName;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "EXAM_DATE")
	@JsonFormat(shape = Shape.STRING , pattern = "yyyy-MM-dd")
	private Date examDate;
	
	@Column(name = "SEMESTER")
	private String semester;
	
	@Column(name = "EXAM_TIME")
	private String examTime;
	
	
	
	//setter getter
	
	
	public String getSubjectName() {
		return subjectName;
	}

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
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map=new  LinkedHashMap<>();
		map.put("subjectName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
