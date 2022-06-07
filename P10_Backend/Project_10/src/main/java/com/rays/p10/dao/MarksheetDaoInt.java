package com.rays.p10.dao;

import java.util.List;

import com.rays.p10.common.BaseDaoInt;
import com.rays.p10.common.UserContext;
import com.rays.p10.dto.MarksheetDTO;

public interface MarksheetDaoInt extends BaseDaoInt<MarksheetDTO>{

	
	/**
	 * Get merit list
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<MarksheetDTO> getMeritList();
}
