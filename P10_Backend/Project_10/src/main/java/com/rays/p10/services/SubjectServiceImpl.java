package com.rays.p10.services;

import org.springframework.stereotype.Service;

import com.rays.p10.common.BaseServiceImpl;
import com.rays.p10.dao.SubjectDaoInt;
import com.rays.p10.dto.SubjectDTO;

@Service
public class SubjectServiceImpl extends BaseServiceImpl<SubjectDTO, SubjectDaoInt> implements SubjectServiceInt{

}
