package com.rays.p10.services;

import org.springframework.stereotype.Service;

import com.rays.p10.common.BaseServiceImpl;
import com.rays.p10.dao.TimeTableDaoInt;
import com.rays.p10.dto.TimeTableDTO;

@Service
public class TimeTableServiceImpl extends BaseServiceImpl<TimeTableDTO, TimeTableDaoInt> implements TimeTableServiceInt{

}
