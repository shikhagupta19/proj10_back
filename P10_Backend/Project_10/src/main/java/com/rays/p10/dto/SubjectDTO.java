package com.rays.p10.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.p10.common.BaseDTO;


@Entity
@Table(name = "SB_SUBJECT")
public class SubjectDTO extends BaseDTO{

	
	/* Name of Subject. */
	@Column(name = "SUBJECT_NAME")
	private String subjectName;

	/* Description of Subject. */
	@Column(name = "DESCRIPTION")
	private String description;

	/* CourseId of Subject. */
	@Column(name = "COURSE_ID")
	private long courseId;

	/* Course Name of Subject. */
	@Column(name = "COURSE_NAME")
	private String courseName;
	
	
	//getter Setter-----------------------------
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

	@Override
	public String getKey() {
		return id + "";
	}
	
	@Override
	public String getValue() {
		return subjectName;
	}
	
	
	@Override
	public LinkedHashMap<String, String> orderBY() {
		
		LinkedHashMap<String, String> map= new LinkedHashMap<>();
		map.put("subjectName", "asc");
		
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		//checkDuplicate
		LinkedHashMap<String, Object> map= new LinkedHashMap<>();
		map.put("subjectName", subjectName);
		
		return map;
	}

}
