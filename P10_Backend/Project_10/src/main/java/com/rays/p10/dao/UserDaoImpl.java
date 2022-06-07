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
import com.rays.p10.dto.RoleDTO;
import com.rays.p10.dto.UserDTO;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserDTO> implements UserDaoInt{

	@Override
	public Class<UserDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return UserDTO.class;
	}
	

	@Override
	protected List<Predicate> getWhereClause(UserDTO dto, CriteriaBuilder builder, Root<UserDTO> qRoot) {

		System.out.println("inside WhereClause in userDaoImpl-------------");
		
		List<Predicate> whereCondition= new ArrayList<Predicate>();
		
		if(!isEmptyString(dto.getFirstName())) {
			whereCondition.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
		}

		if(!isEmptyString(dto.getLastName())) {
			whereCondition.add(builder.like(qRoot.get("lastName"), dto.getLastName() + "%"));
		}
		
		if(!isEmptyString(dto.getRoleName())) {
			whereCondition.add(builder.like(qRoot.get("roleName"), dto.getRoleName() + "%"));
		}
		
		if(!isZeroNumber(dto.getRoleId())) {
			whereCondition.add(builder.equal(qRoot.get("roleId"), dto.getRoleId() ));
		}
		
		if(!isEmptyString(dto.getGender())) {
			whereCondition.add(builder.like(qRoot.get("gender"), dto.getGender() + "%"));
		}
		
		if(!isEmptyString(dto.getPhone())) {
			whereCondition.add(builder.like(qRoot.get("phone"), dto.getPhone() + "%"));
		}
		
		if(!isEmptyString(dto.getOrgName())) {
			whereCondition.add(builder.like(qRoot.get("orgName"), dto.getOrgName() + "%"));
		}
		
		if(!isEmptyString(dto.getEmail())) {
			whereCondition.add(builder.equal(qRoot.get("email"), dto.getEmail() ));
		}
		
		
		return whereCondition;
	}

	@Autowired
	RoleDaoInt roleDaoInt;
	
	@Override
	protected void populate(UserDTO dto, UserContext userContext) {
		
	System.out.println("call populate for add rolename by roleId-------");
	
	if(dto.getRoleId()!=null && dto.getRoleId() > 0) {
		RoleDTO rdto=roleDaoInt.findByPK(dto.getRoleId(), userContext);
		dto.setRoleName(rdto.getName());
	
	}
	
	}
	
}
