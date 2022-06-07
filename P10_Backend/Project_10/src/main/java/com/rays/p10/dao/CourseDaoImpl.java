package com.rays.p10.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.p10.common.BaseDaoImpl;
import com.rays.p10.common.UserContext;
import com.rays.p10.dto.CollegeDTO;
import com.rays.p10.dto.CourseDTO;

@Repository
public class CourseDaoImpl extends BaseDaoImpl<CourseDTO> implements CourseDaoInt{

	
	
	@Override
	public Class<CourseDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return CourseDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(CourseDTO dto, CriteriaBuilder builder, Root<CourseDTO> qRoot) {
		
		System.out.println("inside getWhereClause of CourseDaoImpl--------------- ");
		
		List<Predicate> whereCondition= new ArrayList<Predicate>();
		
		if(!isEmptyString(dto.getName())) {
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
	
		if(!isEmptyString(dto.getDuration())) {
			whereCondition.add(builder.like(qRoot.get("duration"), dto.getDuration() + "%"));
		}
	
		if(!isZeroNumber(dto.getId())) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}
	
		return whereCondition;
	}
	
	@Override
	protected void populate(CourseDTO dto, UserContext userContext) {
		// TODO Auto-generated method stub
		super.populate(dto, userContext);
	}

}
