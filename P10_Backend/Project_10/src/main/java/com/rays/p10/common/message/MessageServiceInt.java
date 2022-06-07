package com.rays.p10.common.message;

import com.rays.p10.common.BaseServiceInt;
import com.rays.p10.common.UserContext;

/**
 * College Service interface.
 */

public interface MessageServiceInt extends BaseServiceInt<MessageDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public MessageDTO findByTitle(String name, UserContext userContext);

	public MessageDTO findByCode(String code, UserContext userContext);

}
