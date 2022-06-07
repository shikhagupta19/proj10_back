package com.rays.p10.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import com.rays.p10.exception.DuplicateRecordException;

public abstract class BaseDaoImpl<T extends BaseDTO> implements BaseDaoInt<T> {

	@PersistenceContext
	protected
	EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		// this.sessionFactory =
		// entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
	}
	
	
	/**
	 * Get DTO Class object
	 * 
	 * @return
	 */
	public abstract Class<T> getDTOClass();

	/**
	 * Creates WHERE clause of search
	 * 
	 * @param dto
	 * @param builder
	 * @param qRoot
	 * @return
	 */
	protected abstract List<Predicate> getWhereClause(T dto, CriteriaBuilder builder, Root<T> qRoot);

	
	TypedQuery<T> createCriteria(T dto, UserContext uc){
	
		System.out.println("inside createcriteria--------");
		
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		
		CriteriaQuery<T> cq=cb.createQuery(getDTOClass());
		
		Root<T> qroot=cq.from(getDTOClass());
		
		cq.select(qroot);
		
		System.out.println("call getwhereClause for where condition------------");
		List<Predicate> whereClause=getWhereClause(dto, cb, qroot);
		
		if(whereClause!=null) {
			System.out.println("whereClause is not  null");
			System.out.println(cb+"_+__+_+_+_+_+_+_+_+"+qroot);
			
			
		}
//		 Put organization filter
//		if (dto.isGroupFilter()) {
//			System.out.println("inside GroupFilter");
//			whereClause.add(cb.equal(qroot.get("orgId"), dto.getOrgId()));
//		}
		
		cq.where(whereClause.toArray(new Predicate[whereClause.size()]));
		
		System.out.println("call orderBy for ordering Result---------");
		
		List<Order> orderBys= getOrderByClause(dto, cb, qroot);
		cq.orderBy(orderBys.toArray(new Order[orderBys.size()]));
		
		TypedQuery<T> tq=entityManager.createQuery(cq);
		
		
		return tq;
		
	}
	
	/**
	 * Get order by clause
	 * 
	 * @param dto
	 * @param builder
	 * @param qRoot
	 * @return
	 */
	protected List<Order> getOrderByClause(T dto, CriteriaBuilder builder, Root<T> qRoot) {
	
		//here we call orderBy of DTO override method of BaseDTO for asc && desc Orders
		
		System.out.println("inside orderBy-------------");
		LinkedHashMap<String, String> map= dto.orderBY(); 
		
		List orderby= new ArrayList();
		
		map.forEach((key, value) -> {
		
			if(value.equals("asc")) {
				orderby.add(builder.asc(qRoot.get(key)));
			}
			else {
				orderby.add(builder.desc(qRoot.get(key)));
			}
		});
		
		return orderby;
	
	}
	
	@Override
	public long add(T dto, UserContext userContext) {
		
		System.out.println("inside Add BaseDaoImpl--------------");
		
		checkDuplicate(dto, userContext);
		
		dto.setCreatedBy(userContext.getEmail());
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedBy(userContext.getEmail());
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		dto.setOrgId(userContext.getOrgId());
		dto.setOrgName(userContext.getOrgName());
		entityManager.persist(dto);
		
		populate(dto, userContext); //for set Rolename by RoleId from override in DaoImpl-------
		return dto.getId();
	}

	/**
	 * Populate redundant values into dto. Overridden by chiled classes.
	 * 
	 * @param dto
	 */
	protected void populate(T dto, UserContext userContext) {

	}

	/**
	 * Check unique keys
	 * 
	 * @param dto
	 */
	private void checkDuplicate(T dto, UserContext userContext) {
		
		System.out.println("check duplicate inside baseDao_Impl");
		LinkedHashMap<String, Object> uniqueKeys = dto.uniqueKeys();
		if (uniqueKeys == null) {
			return;
		}
		uniqueKeys.forEach((key, value) -> {
			T dtoExist = findByUniqueKey(key, value, userContext);
			System.out.println("key "+key+" value "+value);
			if (dtoExist != null && dto.getId() != dtoExist.getId()) {
				throw new DuplicateRecordException(key + " already exists");
			}
		});
	}
	
	@Override
	public void update(T dto, UserContext userContext) {
		System.out.println("inside update BaseDaoImpl---------");

		populate(dto, userContext); //for set Rolename by RoleId from override in DaoImpl-------
		
		checkDuplicate(dto, userContext); //for duplicate
		
		entityManager.merge(dto);
		
		
	}

	@Override
	public void delete(T dto, UserContext userContext) {
		System.out.println("inside delete basedaoIMpl--------");
		
		entityManager.remove(dto);
		
	}

	@Override
	public T findByPK(long pk, UserContext userContext) {
		
		System.out.println("inside findbyId BaseDaoImpl-----------");
		
		T dto = entityManager.find(getDTOClass(), pk);
		
		return dto;
	}

	@Override
	public T findByUniqueKey(String attribute, Object val, UserContext userContext) {
		System.out.println("inside findByUniqueKeys in baseDaoImpl--------------------");
		
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		
		CriteriaQuery<T> cq=cb.createQuery(getDTOClass());
		
		Root qRoot=cq.from(getDTOClass());
		
		Predicate condition=cb.equal(qRoot.get(attribute), val);
		
		cq.where(condition);
		
		TypedQuery<T> tq=entityManager.createQuery(cq);
		
		List list= tq.getResultList();
		
		T dto=null;
		
		if(list.size() > 0)
		{
			dto= (T) list.get(0);
		}
		return dto;
	}

	@Override
	public List search(T dto, int pageNo, int pageSize, UserContext userContext) {

		System.out.println("inside SearchBy BaseDao IMpl-------------");
		
		System.out.println("call createCriteria method");
		TypedQuery<T> query= createCriteria(dto, userContext);
		
		if(pageSize > 0) {
			System.out.println("inside pagination in search BaseDaoImpl----------");
			
			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
		}
		
		List<T> list = query.getResultList();
//		System.out.println(list.size()+"-----------------------");
		if(list.size() == 0) {
			System.out.println("not record--------------");
		return list;
		}
		else {
		System.out.println("record found");
			return list;

		}
		}

	@Override
	public List search(T dto, UserContext userContext) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0, userContext);
	}

	@Override
	public List runHQL(String hql, UserContext userContext) {
		Query q = entityManager.createQuery(hql);
		List list = q.getResultList();
		return list;
	}

	
	
	/**
	 * Check empty string
	 * 
	 * @param val
	 * @return
	 */
	protected boolean isEmptyString(String val) {
		return val == null || val.trim().length() == 0;
	}

	/**
	 * Check zero number
	 * 
	 * @param val
	 * @return
	 */
	protected boolean isZeroNumber(Long val) {
		return val == null || val == 0;
	}

	/**
	 * Check zero number
	 * 
	 * @param val
	 * @return
	 */

	protected boolean isZeroNumber(Integer val) {
		return val == null || val == 0;
	}

	protected boolean isNotNull(Object val) {
		return val != null;
	}
	


}
