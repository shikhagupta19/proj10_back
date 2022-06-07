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
import com.rays.p10.dto.CourseDTO;
import com.rays.p10.dto.FacultyDTO;
import com.rays.p10.dto.SubjectDTO;
import com.rays.p10.services.CollegeServiceInt;
import com.rays.p10.services.CourseServiceInt;
import com.rays.p10.services.SubjectServiceInt;

@Repository
public class FacultyDaoImpl extends BaseDaoImpl<FacultyDTO> implements FacultyDaoInt{

	@Override
	public Class<FacultyDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return FacultyDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(FacultyDTO dto, CriteriaBuilder builder, Root<FacultyDTO> qRoot) {
		
		List<Predicate> whereCondition= new ArrayList<Predicate>();
		
		if(!isEmptyString(dto.getFirstName())) {
			whereCondition.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
		}
		
		if(!isEmptyString(dto.getGender())) {
			whereCondition.add(builder.like(qRoot.get("gender"), dto.getGender() + "%"));
		}
		
		if(!isEmptyString(dto.getQualification())) {
			whereCondition.add(builder.like(qRoot.get("qualification"), dto.getQualification() + "%"));
		}
		
		if(!isEmptyString(dto.getMobileNo())) {
			whereCondition.add(builder.equal(qRoot.get("mobileNo"), dto.getMobileNo() ));
		}
		
		if(!isEmptyString(dto.getEmailId())) {
			whereCondition.add(builder.like(qRoot.get("emailId"), dto.getEmailId() + "%"));
		}
		
//		if(!isNotNull(dto.getDob())) {
//			whereCondition.add(builder.like(qRoot.get("dob"), dto.getDob() + "%"));
//		}
		
		if(!isEmptyString(dto.getCollegeName())) {
			whereCondition.add(builder.like(qRoot.get("collegeName"), dto.getCollegeName() + "%"));
		}
		
		if(!isEmptyString(dto.getCourseName())) {
			whereCondition.add(builder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
		}
		
		if(!isEmptyString(dto.getSubjectName())) {
			whereCondition.add(builder.like(qRoot.get("subjectName"), dto.getSubjectName() + "%"));
		}
		
		if(!isZeroNumber(dto.getId())) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId() ));
		}

		if(!isZeroNumber(dto.getCourseId())) {
			whereCondition.add(builder.equal(qRoot.get("courseId"), dto.getCourseId() ));
		}

		if(!isZeroNumber(dto.getCollegeId())) {
			whereCondition.add(builder.equal(qRoot.get("collegeId"), dto.getCollegeId() ));
		}

		if(!isZeroNumber(dto.getSubjectId())) {
			whereCondition.add(builder.equal(qRoot.get("subjectId"), dto.getSubjectId() ));
		}

		return whereCondition;
	}
	
	@Autowired
	CollegeServiceInt collegeServiceInt;
	
	@Autowired
	CourseServiceInt courseServiceInt;
	
	@Autowired
	SubjectServiceInt subjectServiceInt;
	
	@Override
	protected void populate(FacultyDTO dto, UserContext userContext) {
		
		if(dto.getCourseId() > 0  )
		{
			CourseDTO cdto= courseServiceInt.findById(dto.getCourseId(), userContext);
			dto.setCourseName(cdto.getName());
		}
		
		if(dto.getSubjectId() > 0) {
			SubjectDTO sdto= subjectServiceInt.findById(dto.getSubjectId(), userContext);
			dto.setSubjectName(sdto.getSubjectName());
		}
		
		if(dto.getCollegeId() > 0) {
			CollegeDTO collegeDto= collegeServiceInt.findById(dto.getCollegeId(), userContext);
			dto.setCollegeName(collegeDto.getName());
					
		}
	}

}
