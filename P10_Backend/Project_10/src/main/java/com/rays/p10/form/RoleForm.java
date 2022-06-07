package com.rays.p10.form;

import javax.validation.constraints.NotEmpty;

import com.rays.p10.common.BaseDTO;
import com.rays.p10.common.BaseForm;
import com.rays.p10.dto.RoleDTO;

public class RoleForm extends BaseForm{

	@NotEmpty(message = "Role Name must not be empty")
	private String name = null;

	@NotEmpty(message = "Discription must not be empty")
	private String discription = null;

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

	
	@Override
	public BaseDTO getDto() {
		System.out.println("setdto in RoleForm ");
		RoleDTO dto = initDTO(new RoleDTO());
		dto.setName(name);
		dto.setDiscription(discription);
		//System.out.println(dto.getId()+"@@@@@@@@@"+dto.getName()+"@@@@@@@@@@@@@@@@@@@@@@"+dto.getDiscription());
		
		System.out.println("return dto from RoleForm");
		return dto;
	}
	
	@Override
	public void populate(BaseDTO bDto) {
	
	}
	
}
