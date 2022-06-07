package com.rays.p10.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.p10.common.BaseDTO;


@Entity
@Table(name = "SB_COLLEGE")
public class CollegeDTO extends BaseDTO {

	
	
	/**
	 * Name of College
	 */
	@Column(name = "NAME", length = 50)
	private String name;
	/**
	 * Address of College
	 */
	@Column(name = "ADDRESS", length = 50)
	private String address;
	/**
	 * State of College
	 */
	@Column(name = "STATE", length = 50)
	private String state;
	/**
	 * City of College
	 */
	@Column(name = "CITY", length = 50)
	private String city;
	/**
	 * Phoneno of College
	 */
	@Column(name = "PHONENO", length = 15)
	private String phoneNo;

	//Setter Getter
	
	
	
	
	
	
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	
	
	
	////////////////////////////////////
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
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
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		
		map.put("name", name);
		
		return map;
	}

}
