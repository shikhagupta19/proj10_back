package com.rays.p10.common;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author Asus
 *
 */
@MappedSuperclass
public abstract class BaseDTO implements Serializable, Comparable<BaseDTO>, DropDownList{

	
	
	
	
	@Id
	@GeneratedValue(generator = "ncsPk")
	@GenericGenerator(name = "ncsPk", strategy = "increment")
	@Column(name = "ID", unique = true, nullable = false)
	protected Long id;
	/**
	 * Contains USER ID who created this database record
	 */
	@Column(name = "CREATED_BY", length = 50)
	protected String createdBy = "root";
	/**
	 * Contains USER ID who modified this database record
	 */
	@Column(name = "MODIFIED_BY", length = 50)
	protected String modifiedBy = "root";
	/**
	 * Contains Created Timestamp of database record
	 */
	@CreatedDate
	@Column(name = "CREATED_DATETIME")
	protected Timestamp createdDatetime;
	/**
	 * Contains Modified Timestamp of database record
	 */
	@LastModifiedDate
	@Column(name = "MODIFIED_DATETIME")
	protected Timestamp modifiedDatetime;

	@Column(name = "ORG_ID")
	protected Long orgId = 0L;

	@Column(name = "ORG_NAME", length = 50)
	private String orgName;

	
	//getter setter
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	///////////Absract Method 
	
	/**
	 * Order by attributes
	 * 
	 * @return
	 */
	public abstract LinkedHashMap<String, String> orderBY();

	/**
	 * Unique attributes
	 * 
	 * @return
	 */
	public abstract LinkedHashMap<String, Object> uniqueKeys();

	/**
	 * Apply organization filter
	 * 
	 * @return
	 */
	public boolean isGroupFilter() {
		return true;
	}

	
	
	
	

	///////////////////////////////////DropDownList////////////////////////////////////////////
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(BaseDTO o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
