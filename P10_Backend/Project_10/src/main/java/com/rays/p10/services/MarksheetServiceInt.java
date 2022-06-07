package com.rays.p10.services;

import java.util.List;

import com.rays.p10.common.BaseServiceInt;
import com.rays.p10.common.UserContext;
import com.rays.p10.dto.MarksheetDTO;

public interface MarksheetServiceInt extends BaseServiceInt<MarksheetDTO>{

	
	
	/**
	 * Finds marksheet by Roll No
	 * 
	 * @param rollNo
	 * @return
	 */
	public MarksheetDTO findByRollNo(String rollNo, UserContext context);
	
	/**
	 * Gets merit list of students
	 * 
	 * @return
	 */
	public List<MarksheetDTO> getMeritList(UserContext context);
}
