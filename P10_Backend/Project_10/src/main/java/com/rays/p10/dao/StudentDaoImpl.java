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
import com.rays.p10.dto.CollegeDTO;
import com.rays.p10.dto.StudentDTO;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<StudentDTO> implements StudentDaoInt{

	@Override
	public Class<StudentDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return StudentDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(StudentDTO dto, CriteriaBuilder builder, Root<StudentDTO> qRoot) {
		System.out.println("getwhereClause in StdDaoImpl----------------");
	
		List<Predicate> whereCondition=new ArrayList<Predicate>();
		
		if(!isEmptyString(dto.getFirstName())) {
			whereCondition.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
		}
		
		if(!isEmptyString(dto.getLastName())) {
			whereCondition.add(builder.like(qRoot.get("lastName"), dto.getLastName() + "%"));
		}

		if(!isEmptyString(dto.getMobileNo())) {
			whereCondition.add(builder.equal(qRoot.get("mobileNo"), dto.getMobileNo() ));
		}

		if(!isEmptyString(dto.getCollegeName())) {
			whereCondition.add(builder.like(qRoot.get("collegeName"), dto.getCollegeName() + "%"));
		}
		

		if(!isEmptyString(dto.getEmail())) {
			whereCondition.add(builder.like(qRoot.get("email"), dto.getEmail() + "%"));
		}
		

		if(!isZeroNumber(dto.getId())) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId() ));
		}
		return whereCondition;
	}
	
	@Autowired
	CollegeDaoInt collegeDaoInt;
	
	@Override
	protected void populate(StudentDTO dto, UserContext userContext) {
	
	System.out.println("inside populate STD DaoIMpl---------");
	if(dto.getCollegeId()!=null && dto.getCollegeId() > 0) {
		
		CollegeDTO cdto = collegeDaoInt.findByPK(dto.getCollegeId(), userContext);
		
		dto.setCollegeName(cdto.getName());
	}
		
	}

}
