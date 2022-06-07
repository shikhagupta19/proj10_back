package com.rays.p10.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.p10.common.BaseCtl;
import com.rays.p10.common.ORSResponse;
import com.rays.p10.dto.CollegeDTO;
import com.rays.p10.dto.StudentDTO;
import com.rays.p10.form.StudentForm;
import com.rays.p10.services.CollegeServiceInt;
import com.rays.p10.services.StudentServiceInt;

@RestController
@RequestMapping("/Student")
public class StudentCtl extends BaseCtl<StudentForm, StudentDTO, StudentServiceInt> {

	
	@Autowired
	private CollegeServiceInt collegeService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<CollegeDTO> list = collegeService.search(new CollegeDTO(), userContext);
		res.addResult("collegeList", list);
		return res;
	}
	
	public StudentCtl() {
	
	System.out.println("inside Student Ctl---------------");
	}
	
	
	
	
}
