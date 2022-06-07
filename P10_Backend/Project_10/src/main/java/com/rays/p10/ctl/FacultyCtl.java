package com.rays.p10.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.p10.common.BaseCtl;
import com.rays.p10.common.DropDownList;
import com.rays.p10.common.ORSResponse;
import com.rays.p10.dto.CollegeDTO;
import com.rays.p10.dto.CourseDTO;
import com.rays.p10.dto.FacultyDTO;
import com.rays.p10.dto.RoleDTO;
import com.rays.p10.dto.SubjectDTO;
import com.rays.p10.dto.UserDTO;
import com.rays.p10.form.FacultyForm;
import com.rays.p10.services.CollegeServiceInt;
import com.rays.p10.services.CourseServiceInt;
import com.rays.p10.services.FacultyServiceInt;
import com.rays.p10.services.SubjectServiceInt;

@RestController
@RequestMapping("Faculty")
public class FacultyCtl extends BaseCtl<FacultyForm, FacultyDTO, FacultyServiceInt> {

	public FacultyCtl() {
	System.out.println("inside faculty Ctl=-------------------");
	}
	
	@Autowired
	CollegeServiceInt collegeServiceInt;
	
	@Autowired
	CourseServiceInt courseServiceInt;
	
	@Autowired
	SubjectServiceInt subjectServiceInt;

	@GetMapping("/preload")
	public ORSResponse preload() {
		
		System.out.println("inside preload of UserCtl");
		ORSResponse res = new ORSResponse(true);
		CollegeDTO clgdto = new CollegeDTO();
		SubjectDTO sdto=new SubjectDTO();
		CourseDTO codto=new CourseDTO();
		UserDTO udto=new UserDTO();
		//dto.setStatus(RoleDTO.ACTIVE);
		List<DropDownList> list = collegeServiceInt.search(clgdto, userContext);
		res.addResult("clgList", list); 
		
		List<DropDownList> colist=courseServiceInt.search(codto, userContext);
		
		res.addResult("coList", colist);
		
		List<DropDownList> slist = subjectServiceInt.search(sdto, userContext);
		res.addResult("subList", slist); 
		
		return res;
	}
	
}
