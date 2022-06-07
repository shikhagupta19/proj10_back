package com.rays.p10.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.p10.common.BaseServiceImpl;
import com.rays.p10.common.UserContext;
import com.rays.p10.common.mail.EmailDTO;
import com.rays.p10.common.mail.EmailServiceImpl;
import com.rays.p10.dao.UserDaoInt;
import com.rays.p10.dto.UserDTO;

@Service

public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDaoInt> implements UserServiceInt {

	
	@Autowired
	EmailServiceImpl emailService = null;
	
	@Transactional(readOnly = true)
	public UserDTO findByLoginId(String login, UserContext userContext) {
		System.out.println("inside findbyLogin: ==== "+login);
		return baseDaoInt.findByUniqueKey("email", login, userContext);
	}
	
	@Override
	@Transactional
	public UserDTO changePassword(String loginId, String oldPassword, String newPassword, UserContext userContext) {
		
		System.out.println("inside UserServimpl changepass+++++++++");
		UserDTO dto=findByLoginId(loginId, null);
		
		if(dto==null) {
			return null;
		}
		else {
		if(oldPassword.equals(dto.getPassword())){
			System.out.println("set new password "+dto.getPassword());
			dto.setPassword(newPassword);
			update(dto,userContext);
			return dto;
		}
		else {
		return null;
	}
		}
	}

	@Override
	@Transactional
	public UserDTO findByEmail(String email, UserContext userContext) {
		
		return baseDaoInt.findByUniqueKey("email", email, userContext);
	}

	
	/**
	 * Authenticate a user
	 */
	@Override
	@Transactional
	public UserDTO authenticate(String loginId, String password) {
		
		System.out.println("inside authenticate in UserServImpl");
		UserDTO dto= findByEmail(loginId, null);
		if(dto!=null) {
			UserContext uc=new UserContext(dto);
		
			if(password.equals(dto.getPassword())) {
				dto.setLastLogin(new Timestamp(new Date().getTime()));
				dto.setUnsucessfullLoginAttempt(0);
				update(dto, uc);
				return dto;
			}
			else {
				dto.setUnsucessfullLoginAttempt(1+dto.getUnsucessfullLoginAttempt());
				update(dto, uc);
			}
		}
		else {
			System.out.println("gmail id wrong");
		}
		
		return null;
	}

	@Override
	public UserDTO forgotPassword(String loginId) {
		
		
		System.out.println("forgetPassword in UserService");
		UserDTO dto = findByLoginId(loginId, null);

		if (dto == null) {
			return null;
		}

		EmailDTO emailDTO = new EmailDTO();
		emailDTO.addTo(dto.getEmail());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("user", dto.getFirstName() + " " + dto.getLastName());
		System.out.println(dto.getFirstName()+ dto.getLastName());
		
		params.put("password", dto.getPassword());
		System.out.println(dto.getPassword());
		emailDTO.setMessageCode("U-FP", params);

		emailService.send(emailDTO, null);

		return dto;
	}

		
	

	
	
	/**
	 * Register new user
	 */

	@Override
	@Transactional
	public UserDTO register(UserDTO dto) {

		UserContext uc =new UserContext();
		uc.setEmail("registerroot@gmail.com");
		uc.setOrgId(5L);
		uc.setOrgName("My ORG");
		
		Long id=add(dto, uc);
		
		dto.setId(id);
		
		
		return dto;
	}	
	
	

	
	
}
