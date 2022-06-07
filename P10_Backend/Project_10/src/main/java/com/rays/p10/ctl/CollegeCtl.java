package com.rays.p10.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.p10.common.BaseCtl;
import com.rays.p10.common.ORSResponse;
import com.rays.p10.dto.CollegeDTO;
import com.rays.p10.form.CollegeForm;
import com.rays.p10.services.CollegeServiceInt;

@RestController
@RequestMapping("/College")
public class CollegeCtl extends BaseCtl<CollegeForm, CollegeDTO, CollegeServiceInt>{

	@Autowired
	CollegeServiceInt collegeServiceInt;
	
	
	public CollegeCtl() {
	
	System.out.println("inside CollegeCtl");
	}


	@GetMapping("/preload")
	public ORSResponse preload() {
	
		System.out.println("inside preload of ClgCtl--------------");
		
		ORSResponse res =new ORSResponse(true);
		
		CollegeDTO dto= new CollegeDTO();
		
		List list= collegeServiceInt.search(dto, userContext);
		
		res.addResult("cList", list);
		
		return res;
		
	}

}
