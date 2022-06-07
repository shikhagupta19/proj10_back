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
import com.rays.p10.dto.MarksheetDTO;
import com.rays.p10.dto.StudentDTO;
import com.rays.p10.services.StudentServiceInt;

@Repository
public class MarksheetDaoImpl extends BaseDaoImpl<MarksheetDTO> implements MarksheetDaoInt{

	@Override
	public Class<MarksheetDTO> getDTOClass() {
		
		return MarksheetDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(MarksheetDTO dto, CriteriaBuilder builder, Root qRoot) {
		
		System.out.println("inside get whereClause in MarksheetDaoImpl ------------------");
		
		List<Predicate> whereCondition = new ArrayList<Predicate>();
		if(!isEmptyString(dto.getName())) {
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		
		if(!isEmptyString(dto.getRollNo())) {
			whereCondition.add(builder.equal(qRoot.get("rollNo"), dto.getRollNo()));
		}
		
		if(!isZeroNumber(dto.getChemistry())) {
			whereCondition.add(builder.equal(qRoot.get("chemistry"), dto.getChemistry() ));
			
		}
		

		if(!isZeroNumber(dto.getPhysics())) {
			whereCondition.add(builder.equal(qRoot.get("physics"), dto.getPhysics() ));
			
		}
		

		if(!isZeroNumber(dto.getMaths())) {
			whereCondition.add(builder.equal(qRoot.get("maths"), dto.getMaths() ));
			
		}
		return whereCondition;
	}
	
	@Autowired
	StudentServiceInt studentServiceInt;
	
	@Override
	protected void populate(MarksheetDTO dto, UserContext userContext) {
		
		System.out.println("inside marksheetdaoImpl populate=-----------");
		
		if(dto.getStudentId()!=null && dto.getStudentId() > 0 ) {
			StudentDTO sdto=studentServiceInt.findById(dto.getStudentId(), userContext);
			dto.setName(sdto.getFirstName() + " " + sdto.getLastName());
		}
		
		
	}

	@Override
	public List<MarksheetDTO> getMeritList() {
		
		System.out.println("marksheetDao merit marksheett run start");
		List list = super.runHQL("from MarksheetDTO order by (physics+chemistry+maths) desc", null);
		

		return list;
	}

	
}
