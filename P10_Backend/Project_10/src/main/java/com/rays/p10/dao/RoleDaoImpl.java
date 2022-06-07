package com.rays.p10.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.p10.common.BaseDaoImpl;
import com.rays.p10.dto.RoleDTO;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<RoleDTO> implements RoleDaoInt {

	@Override
	public Class<RoleDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return RoleDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(RoleDTO dto, CriteriaBuilder builder, Root<RoleDTO> qRoot) {
		
		System.out.println("inside whereClause of roleDao");
		
		System.out.println(builder+"_+__+_+_+_+_+_+_+_+"+qRoot); 
		
		List<Predicate> whereCondition= new ArrayList<>();
		
		
		if(!isEmptyString(dto.getName())) {
			System.out.println("if search by roleName inside Role Dao");
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
			
		}
		
		if(!isZeroNumber(dto.getId())) {
			System.out.println("if search by Description inside Role Dao");
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}
		
		return whereCondition;
	}

}
