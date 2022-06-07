package com.rays.p10.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.p10.common.BaseCtl;
import com.rays.p10.common.DropDownList;
import com.rays.p10.common.ORSResponse;
import com.rays.p10.dto.CourseDTO;
import com.rays.p10.form.CourseForm;
import com.rays.p10.services.CourseServiceInt;


@RestController
@RequestMapping("Course")
public class CourseCtl extends BaseCtl<CourseForm, CourseDTO, CourseServiceInt>{

	@Autowired
	CourseServiceInt courseServiceInt;
	
	public CourseCtl() {
	
	System.out.println("inside CourseCtl");
	
	}
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		
		System.out.println("inside preload of UserCtl");
		ORSResponse res = new ORSResponse(true);
		
		CourseDTO dto=new CourseDTO();
		List<DropDownList> list = courseServiceInt.search(dto, userContext);
		res.addResult("crslist", list); 
		return res;
	}
}
