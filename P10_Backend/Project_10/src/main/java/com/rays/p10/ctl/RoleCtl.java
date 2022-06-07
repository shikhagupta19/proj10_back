package com.rays.p10.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.p10.common.BaseCtl;
import com.rays.p10.common.DropDownList;
import com.rays.p10.common.ORSResponse;
import com.rays.p10.dto.RoleDTO;
import com.rays.p10.form.RoleForm;
import com.rays.p10.services.RoleServiceInt;

@RestController
@RequestMapping("/Role")
public class RoleCtl extends BaseCtl<RoleForm, RoleDTO, RoleServiceInt> {

	
	@Autowired
	RoleServiceInt roleServiceInt;
	
	public RoleCtl() {
	
	System.out.println("inside RoleCtl------------");
	}
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		
		System.out.println("inside preload of UserCtl");
		ORSResponse res = new ORSResponse(true);
		RoleDTO dto = new RoleDTO();
		//dto.setStatus(RoleDTO.ACTIVE);
		List<DropDownList> list = roleServiceInt.search(dto, userContext);
		res.addResult("roleList", list); 
		return res;
	}
	
}
