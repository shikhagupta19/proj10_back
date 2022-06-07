package com.rays.p10.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.p10.common.BaseDTO;

@Entity
@Table(name = "SB_ROLE")
public class RoleDTO extends BaseDTO{

	

	public static String YES = "Y";
	public static String NO = "N";
	public static String ACTIVE = "Active";
	public static String INACTIVE = "Inactive";

	@Column(name = "NAME", length = 50)
	private String name = null;

	@Column(name = "DISCRIPTION", length = 100)
	private String discription = null;

	@Column(name = "CAN_READ", length = 1)
	private String canRead = YES;

	@Column(name = "CAN_WRITE", length = 1)
	private String canWrite = YES;

	@Column(name = "CAN_UPDATE", length = 1)
	private String canUpdate = YES;

	@Column(name = "CAN_DELETE", length = 1)
	private String canDelete = YES;

	@Column(name = "STATUS", length = 15)
	private String status = ACTIVE;

	
	
	
	
	
	//setter Getter
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getCanRead() {
		return canRead;
	}

	public void setCanRead(String canRead) {
		this.canRead = canRead;
	}

	public String getCanWrite() {
		return canWrite;
	}

	public void setCanWrite(String canWrite) {
		this.canWrite = canWrite;
	}

	public String getCanUpdate() {
		return canUpdate;
	}

	public void setCanUpdate(String canUpdate) {
		this.canUpdate = canUpdate;
	}

	public String getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(String canDelete) {
		this.canDelete = canDelete;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//get key && Get Value
	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return name;
	}
	
	@Override
	public LinkedHashMap<String, String> orderBY() {
		System.out.println("inside OrderBy RoleDto for Asc && Desc");
		LinkedHashMap<String, String> map= new LinkedHashMap<>();
		
		map.put("name", "asc");
		return map;
		
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
LinkedHashMap<String, Object> map= new LinkedHashMap<>();
		
		map.put("name", name);
		return map;
	}

}
