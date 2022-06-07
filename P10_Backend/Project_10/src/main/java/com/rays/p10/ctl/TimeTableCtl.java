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
import com.rays.p10.dto.SubjectDTO;
import com.rays.p10.dto.TimeTableDTO;
import com.rays.p10.dto.UserDTO;
import com.rays.p10.form.TimeTableForm;
import com.rays.p10.services.CourseServiceInt;
import com.rays.p10.services.SubjectServiceInt;
import com.rays.p10.services.TimeTableServiceInt;

@RestController
@RequestMapping("TT")
public class TimeTableCtl extends BaseCtl<TimeTableForm, TimeTableDTO, TimeTableServiceInt>{

	public TimeTableCtl() {
	
		System.out.println("inside TT Ctl---------------");
	}
	
	
	@Autowired
	CourseServiceInt courseServiceInt;
	
	@Autowired
	SubjectServiceInt subjectServiceInt;

	@GetMapping("/preload")
	public ORSResponse preload() {
		
		System.out.println("inside preload of UserCtl");
		ORSResponse res = new ORSResponse(true);
		
		SubjectDTO sdto=new SubjectDTO();
		CourseDTO codto=new CourseDTO();
		
		
		List<DropDownList> colist=courseServiceInt.search(codto, userContext);
		
		res.addResult("coList", colist);
		
		List<DropDownList> slist = subjectServiceInt.search(sdto, userContext);
		res.addResult("subList", slist); 
		
		return res;
	}

}
