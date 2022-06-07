package com.rays.p10.common;

import com.rays.p10.dto.UserDTO;

public class UserContext {

	private Long userId = 0L;
	private String email = "root";
	private String name = null;
	private Long defaultRoleId = 0L;
	private String roleName = "root";
	private Long orgId = 0L;
	private String orgName = "root";
	private Long orgImageId = 0L;
	
	private UserDTO userDTO = null;

	public UserContext() {
		System.out.println("inside userContext");
	}
	
	UserDTO userDto=null;
	
	
	public UserContext(UserDTO dto) {
		System.out.println("inside userDto in UserContext!!!!!!!!!!!!!!!!!!!!!"+ dto.getOrgName());
		this.userDTO = dto;
		this.email = dto.getEmail();
		this.name = dto.getName();
		this.defaultRoleId = dto.getRoleId();
		this.orgId = dto.getOrgId();
		this.roleName = dto.getRoleName();
		this.userId = dto.getId();
		this.orgName=dto.getOrgName();
		this.userDto=dto;
		System.out.println("____________ user context ------------ " + name);
		System.out.println("rolename in userconyext----------"+dto.getRoleName());
		/*
		 * if (roles == null && userDTO != null) { roles = new
		 * HashSet<String>(); Iterator<UserRoleDTO> it =
		 * userDTO.getUserRoles().iterator(); while (it.hasNext()) {
		 * roles.add(it.next().getRoleName()); } }
		 */

	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getDefaultRoleId() {
		return defaultRoleId;
	}


	public void setDefaultRoleId(Long defaultRoleId) {
		this.defaultRoleId = defaultRoleId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
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


	public Long getOrgImageId() {
		return orgImageId;
	}


	public void setOrgImageId(Long orgImageId) {
		this.orgImageId = orgImageId;
	}


	public UserDTO getUserDTO() {
		return userDTO;
	}


	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	
	
	
}
