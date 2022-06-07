package com.rays.p10.services;

import org.springframework.stereotype.Service;

import com.rays.p10.common.BaseServiceImpl;
import com.rays.p10.dao.FacultyDaoInt;
import com.rays.p10.dto.FacultyDTO;

@Service
public class FacultyServiceImpl extends BaseServiceImpl<FacultyDTO, FacultyDaoInt> implements FacultyServiceInt{

}
