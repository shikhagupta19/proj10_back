package com.rays.p10.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.p10.common.BaseDaoImpl;
import com.rays.p10.common.UserContext;
import com.rays.p10.dto.CourseDTO;
import com.rays.p10.dto.SubjectDTO;
import com.rays.p10.services.CourseServiceInt;

@Repository
public class SubjectDaoImpl extends BaseDaoImpl<SubjectDTO> implements SubjectDaoInt{

	@Override
	public Class<SubjectDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return SubjectDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(SubjectDTO dto, CriteriaBuilder builder, Root<SubjectDTO> qRoot) {
System.out.println("inside getWhereClause of CourseDaoImpl--------------- ");
		
		List<Predicate> whereCondition= new ArrayList<Predicate>();
		
		if(!isEmptyString(dto.getSubjectName())) {
			whereCondition.add(builder.like(qRoot.get("subjectName"), dto.getSubjectName() + "%"));
		}
	
		if(!isEmptyString(dto.getDescription())) {
			whereCondition.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
		}
	
		if(!isZeroNumber(dto.getId())) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}
		
		if(!isZeroNumber(dto.getCourseId())) {
			whereCondition.add(builder.equal(qRoot.get("courseId"), dto.getCourseId()));
		}
		
		if(!isEmptyString(dto.getCourseName())) {
			whereCondition.add(builder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
		}

		return whereCondition;
	}

	@Autowired
	CourseServiceInt courseServiceInt;
	
	@Override
	protected void populate(SubjectDTO dto, UserContext userContext) {
		// TODO Auto-generated method stub
	
		if(dto.getCourseId() > 0) {
			
		CourseDTO cdto=courseServiceInt.findById(dto.getCourseId(), userContext);
		dto.setCourseName(cdto.getName());
	}
		
	}
}
