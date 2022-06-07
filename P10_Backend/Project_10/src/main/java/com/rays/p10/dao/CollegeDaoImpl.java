package com.rays.p10.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.p10.common.BaseDaoImpl;
import com.rays.p10.dto.CollegeDTO;

@Repository
public class CollegeDaoImpl extends BaseDaoImpl<CollegeDTO> implements CollegeDaoInt{

	@Override
	public Class<CollegeDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return CollegeDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(CollegeDTO dto, CriteriaBuilder builder, Root<CollegeDTO> qRoot) {
		
		List<Predicate> whereCondition= new ArrayList<Predicate>();
		
		if(!isEmptyString(dto.getName())) {
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		
		if(!isZeroNumber(dto.getId())) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}
		if(!isEmptyString(dto.getCity())) {
			whereCondition.add(builder.like(qRoot.get("city"), dto.getCity() + "%"));
		}
		
		if(!isEmptyString(dto.getState())) {
			whereCondition.add(builder.like(qRoot.get("state"), dto.getState() + "%"));
		}
		
		if(!isEmptyString(dto.getPhoneNo())) {
			whereCondition.add(builder.like(qRoot.get("phoneNo"), dto.getPhoneNo() + "%"));
		}
		
		return whereCondition;
	}

	
}
