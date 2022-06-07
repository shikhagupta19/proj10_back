package com.rays.p10.services;

import org.springframework.stereotype.Service;

import com.rays.p10.common.BaseServiceImpl;
import com.rays.p10.dao.CollegeDaoInt;
import com.rays.p10.dto.CollegeDTO;

@Service
public class CollegeServiceImpl extends BaseServiceImpl<CollegeDTO, CollegeDaoInt> implements CollegeServiceInt{

}
