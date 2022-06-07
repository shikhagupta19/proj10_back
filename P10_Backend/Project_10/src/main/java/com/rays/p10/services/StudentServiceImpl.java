package com.rays.p10.services;

import org.springframework.stereotype.Service;

import com.rays.p10.common.BaseServiceImpl;
import com.rays.p10.dao.StudentDaoInt;
import com.rays.p10.dto.StudentDTO;

@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentDTO, StudentDaoInt> implements StudentServiceInt{

}
