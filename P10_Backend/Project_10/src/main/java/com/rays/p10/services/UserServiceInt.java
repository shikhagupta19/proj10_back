package com.rays.p10.services;

import com.rays.p10.common.BaseServiceInt;
import com.rays.p10.common.UserContext;
import com.rays.p10.dto.UserDTO;

public interface UserServiceInt extends BaseServiceInt<UserDTO> {

	public UserDTO changePassword(String loginId, String oldPassword, String newPassword, UserContext userContext);
	
	/**
	 * Finds User by name.
	 * 
	 * @param name
	 * @return
	 */
	public UserDTO findByEmail(String email, UserContext userContext);

	public UserDTO authenticate(String email, String password);

	
	public UserDTO forgotPassword(String email);

	public UserDTO register(UserDTO dto);

}
