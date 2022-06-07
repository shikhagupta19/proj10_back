package com.rays.p10.common;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.p10.exception.DatabaseException;
import com.rays.p10.exception.DuplicateRecordException;

/**
 * @author Asus
 *
 * @param <T>
 */

public class BaseServiceImpl<T extends BaseDTO , D extends BaseDaoInt<T>> implements BaseServiceInt<T>{

	@Autowired
	protected D baseDaoInt;
	
	@Override
	@Transactional(readOnly = false)
	public long add(T dto, UserContext userContext) {
		
		System.out.println("inside Add BaseserviceImpl---------");
		long pk=baseDaoInt.add(dto, userContext);
		
		return pk;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto, UserContext userContext) {
		System.out.println("inside update baseServImpl-------------");
		
		T findById = findById(dto.getId(), userContext);
		
		System.out.println("=============" + findById.getModifiedBy());
		dto.setCreatedBy(findById.getCreatedBy());
		dto.setModifiedBy(findById.getModifiedBy());
		dto.setCreatedDatetime(findById.createdDatetime);
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		dto.setOrgName(userContext.getOrgName());
		dto.setOrgId(userContext.getOrgId());
		baseDaoInt.update(dto, userContext);
		
	}

	@Override
	@Transactional
	public long save(T dto, UserContext userContext) {

		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto, userContext);
		} else {
			id = add(dto, userContext);
		}
		return id;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public T delete(long id, UserContext userContext) {
		System.out.println("inside delete of baseserviceImpl-------");
		
		T dto = findById(id, userContext);
		
		if(dto==null){
			throw new DatabaseException("Record Not Found");
		}
		else {
			baseDaoInt.delete(dto, userContext);
			
		}
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public T findById(long id, UserContext userContext) {
		
		System.out.println("inside fingbyId-----------");
		T dto = baseDaoInt.findByPK(id, userContext);
		return dto;
	}

	@Override
	public List search(T dto, int pageNo, int pageSize, UserContext userContext) {
		
		System.out.println("inside SearchBy BaseServImpl---------");
		
		if(dto==null) {
			System.out.println("inside baseserveimpl ======= its preload ");
			
		}
		else {
			System.out.println("dto not null ============");
		}
		List list=baseDaoInt.search(dto, pageNo, pageSize, userContext);
		
		return list;
	}

	@Override
	public List search(T dto, UserContext userContext) {
		
		return search(dto, 0, 0, userContext);
	}

	
		
	
}
