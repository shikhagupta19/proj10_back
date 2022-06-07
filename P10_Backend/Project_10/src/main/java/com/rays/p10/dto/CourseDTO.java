package com.rays.p10.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.p10.common.BaseDTO;

@Entity
@Table(name = "SB_COURSE")
public class CourseDTO extends BaseDTO{

	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DURATION")
	private String duration;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	
	//////setter getter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String getKey() {
		
		return id + "";
	}
	
	@Override
	public String getValue() {
		return name;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		
		LinkedHashMap<String, String> map=new LinkedHashMap<>();
		map.put("name", "asc");
		
		return map;
	}

	
	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {

		//user for check duplicasii
		LinkedHashMap<String, Object> map=new LinkedHashMap<>();
		map.put("name", name);
		
		return map;
	
}
	

}
