package com.rays.p10.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.p10.common.BaseDaoImpl;
import com.rays.p10.common.UserContext;
import com.rays.p10.dto.CourseDTO;
import com.rays.p10.dto.SubjectDTO;
import com.rays.p10.dto.TimeTableDTO;
import com.rays.p10.services.CourseServiceInt;
import com.rays.p10.services.SubjectServiceInt;

@Repository
public class TimeTableDaoImpl extends BaseDaoImpl<TimeTableDTO> implements TimeTableDaoInt{

	@Override
	public Class<TimeTableDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return TimeTableDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(TimeTableDTO dto, CriteriaBuilder builder, Root<TimeTableDTO> qRoot) {
		
		List<Predicate> whereCondition=new ArrayList<Predicate>();
		
		if(!isEmptyString(dto.getSubjectName())) {
			whereCondition.add(builder.like(qRoot.get("subjectName"), dto.getSubjectName() + "%"));
		}
		

		if(!isEmptyString(dto.getCourseName())) {
			whereCondition.add(builder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
		}

		if(!isEmptyString(dto.getSemester())) {
			whereCondition.add(builder.like(qRoot.get("semester"), dto.getSemester() + "%"));
		}

		if(!isZeroNumber(dto.getId())) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId() ));
		}

		if(!isZeroNumber(dto.getSubjectId())) {
			whereCondition.add(builder.equal(qRoot.get("subjectId"), dto.getSubjectId() ));
		}

		if(!isZeroNumber(dto.getCourseId())) {
			whereCondition.add(builder.equal(qRoot.get("courseId"), dto.getCourseId() ));
		}
		
		if(isNotNull(dto.getExamDate())) {
			whereCondition.add(builder.like(qRoot.get("examDate"), dto.getExamDate() + "%"));
		}

		
		return whereCondition;
	}
	
	@Autowired
	CourseServiceInt courseServiceInt;
	
	@Autowired
	SubjectServiceInt subjectServiceInt;
	
	@Override
	protected void populate(TimeTableDTO dto, UserContext userContext) {
	
		System.out.println("inside populate of TTDAOImpl--------------");
		if( dto.getCourseId() > 0 ) {
		
			CourseDTO cdto= courseServiceInt.findById(dto.getCourseId(), userContext);
			dto.setCourseName(cdto.getName());
		}
	
		if(dto.getSubjectId() > 0) {
			SubjectDTO sdto=subjectServiceInt.findById(dto.getSubjectId(), userContext);
			dto.setSubjectName(sdto.getSubjectName());
		}
	
	}

}
