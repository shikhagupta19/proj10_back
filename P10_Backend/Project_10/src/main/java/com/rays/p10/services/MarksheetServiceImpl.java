package com.rays.p10.services;

import com.rays.p10.dto.MarksheetDTO;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.p10.common.BaseServiceImpl;
import com.rays.p10.common.UserContext;
import com.rays.p10.dao.MarksheetDaoInt;

@Service
public class MarksheetServiceImpl extends BaseServiceImpl<MarksheetDTO, MarksheetDaoInt> implements MarksheetServiceInt{

	
	@Override
	@Transactional(readOnly = true)
	public MarksheetDTO findByRollNo(String rollNo, UserContext uc) {
		return baseDaoInt.findByUniqueKey("rollNo", rollNo, uc);
	}

	@Override
	public List<MarksheetDTO> getMeritList(UserContext context) {
		
		System.out.println("getMeritList run in service");
		return  baseDaoInt.getMeritList();
	}
	

}
