package com.rays.p10.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.p10.common.BaseCtl;
import com.rays.p10.common.ORSResponse;
import com.rays.p10.dto.MarksheetDTO;
import com.rays.p10.dto.StudentDTO;
import com.rays.p10.form.MarksheetForm;
import com.rays.p10.services.MarksheetServiceInt;
import com.rays.p10.services.StudentServiceInt;

@RestController
@RequestMapping("/Marksheet")
public class MarksheetCtl extends BaseCtl<MarksheetForm, MarksheetDTO, MarksheetServiceInt> {

	@Autowired
	private StudentServiceInt studentService;

	
	public MarksheetCtl() {
		System.out.println("Inside Marksheet Ctl---------------");
		}
		
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload in markCtl----------");
		ORSResponse res = new ORSResponse(true);
		
		List<StudentDTO> list = studentService.search(new StudentDTO(), userContext);
		res.addResult("studentList", list);
		
		return res;
	}
	
	@GetMapping("rollno/{rollNo}")
	public ORSResponse rollNo(@PathVariable String rollNo) {
		ORSResponse res = new ORSResponse(true);
		MarksheetDTO dto = baseServiceInt.findByRollNo(rollNo, userContext);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}
	
	@GetMapping("meritlist")
	public ORSResponse getMeritList() {
		System.out.println("getMeritList run on ctl" + userContext);
		List<MarksheetDTO> list = baseServiceInt.getMeritList(userContext);
		ORSResponse res = new ORSResponse(true);
		res.addResult("list",list);
		System.out.println(list+"get list on ctl");
		return res;
	}
	
	
}
